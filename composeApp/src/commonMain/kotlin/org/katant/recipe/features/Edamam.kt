package org.katant.recipe.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import org.katant.recipe.Database
import org.katant.recipe.dtos.RecipeEdamamDto
import org.katant.recipe.dtos.SearchEdamamDto
import org.katant.recipe.enums.LoadingEdamamStatus
import org.katant.recipe.store.CreateRecipeState
import java.util.*
import kotlin.math.round

class EdamamClient(
    private var client: HttpClient,
    private var database: Database,
    scope: CoroutineScope
) {
    private var translationFactory = YandexTranslate(client)
    private var baseUrl = "https://api.edamam.com/api"
    private var appId = System.getenv("EDAMAM_APP_ID") ?: ""
    private var appKey = System.getenv("EDAMAM_APP_KEY") ?: ""

    var scope = scope
        private set
    var recipesResponse by mutableStateOf<SearchEdamamDto?>(null)
        private set
    var loadingStatus by mutableStateOf(LoadingEdamamStatus.LOADING)
        private set

    suspend fun getRecipes(query: String) {
        val translations = translationFactory.translateText(
            listOf(query),
            "ru",
            "en"
        )
        loadingStatus = LoadingEdamamStatus.EDAMAM_REQUEST
        val response: HttpResponse = client.request("${baseUrl}/recipes/v2") {
            method = HttpMethod.Get
            parameter("q", translations.translations[0].text)
            parameter("type", "any")
            parameter("app_id", appId)
            parameter("app_key", appKey)
            for (field in listOf("label", "ingredientLines", "ingredients", "calories", "instructionLines")) {
                parameter("field", field)
            }
        }
        recipesResponse = response.body()

        // Filter recipesResponse, recipesResponse must contain in hits only recipes where instructionLines or ingredients is not empty
        recipesResponse!!.hits =
            recipesResponse!!.hits.filter { it.recipe.instructionLines.isNotEmpty() || it.recipe.ingredients.isNotEmpty() }
        // recipes with not empty ingredients must be first
        recipesResponse!!.hits = recipesResponse!!.hits.sortedBy { if (it.recipe.instructionLines.isEmpty()) 1 else 0 }


        loadingStatus = LoadingEdamamStatus.EDAMAM_LOADED
        getRecipeLabels()
        loadingStatus = LoadingEdamamStatus.LOADED
    }

    private suspend fun getRecipeLabels() {
        val labels = recipesResponse?.hits?.map { it.recipe.label } ?: emptyList()
        if (labels.isEmpty()) return
        val translatedLabels = translationFactory.translateText(labels, "en", "ru")
        // Change default labels in recipesResponse.hits to translatedLabels
        recipesResponse?.hits?.forEachIndexed { index, hit ->
            hit.recipe.label = translatedLabels.translations[index].text
        }
    }

    private suspend fun translateRecipe(recipe: RecipeEdamamDto) {
        loadingStatus = LoadingEdamamStatus.TRANSLATING_RECIPE
        if (recipe.ingredients.isNotEmpty()) {
            val translatedIngredientsFood = translationFactory.translateText(
                recipe.ingredients.map { it.food },
                "en", "ru"
            )
            recipe.ingredients.forEachIndexed { ingredientIndex, ingredient ->
                ingredient.food = translatedIngredientsFood.translations[ingredientIndex].text.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
            }
            val translatedIngredientsMeasure = translationFactory.translateText(
                recipe.ingredients.map { it.measure ?: "" },
                "en", "ru"
            )
            recipe.ingredients.forEachIndexed { ingredientIndex, ingredient ->
                if (ingredient.measure == "<unit>") {
                    ingredient.measure = "шт"
                    return@forEachIndexed
                }
                ingredient.measure = translatedIngredientsMeasure.translations[ingredientIndex].text
            }
        }

        if (recipe.instructionLines.isNotEmpty()) {
            val translatedInstructions = translationFactory.translateText(recipe.instructionLines, "en", "ru")
            recipe.instructionLines = translatedInstructions.translations.map { it.text }
        }
        loadingStatus = LoadingEdamamStatus.LOADED
    }

    suspend fun createRecipe(recipeState: CreateRecipeState, recipe: RecipeEdamamDto) {
        translateRecipe(recipe = recipe)
        database.recipeQueries.insert(
            name = recipe.label,
            category = recipeState.category.value,
            calorieContent = recipe.calories.toLong(),
            difficulty = recipeState.difficulty.value,
            photoPath = recipeState.photo.path!!,
        )
        val recipeId = database.recipeQueries.selectLastId().executeAsOne().MAX!!
        recipe.instructionLines.forEachIndexed { index, instruction ->
            database.recipeBodyQueries.insert(
                recipeId = recipeId,
                type = "text",
                body = instruction,
                position = index.toLong(),
            )
        }
        recipe.ingredients.forEach { ingredient ->
            database.recipeIngredientQueries.insert(
                recipeId = recipeId,
                foodName = ingredient.food,
                measure = ingredient.measure ?: "",
                quantity = ingredient.quantity?.let { (round(it * 100) / 100) }
            )
        }
    }
}