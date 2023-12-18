package org.katant.recipe.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import app.cash.sqldelight.db.SqlDriver
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.katant.recipe.Database
import org.katant.recipe.store.RecipeState
import org.katant.recipe.store.WindowState
import org.katant.recipe.enums.WindowsEnum
import org.katant.recipe.features.EdamamClient
import org.katant.recipe.presentation.components.CustomTheme
import org.katant.recipe.presentation.windows.CreateRecipeForm
import org.katant.recipe.presentation.components.MainHeader
import org.katant.recipe.presentation.windows.ChooseRecipe
import org.katant.recipe.presentation.windows.RecipeDetail
import org.katant.recipe.presentation.windows.RecipesList
import org.katant.recipe.store.CreateRecipeState


@Composable
fun App(databaseDriver: SqlDriver, client: HttpClient) {
    val database = Database(databaseDriver)

    val scope  = rememberCoroutineScope()
    val edamamClient = remember { EdamamClient(client, database, scope) }

    val windowState = remember { WindowState() }
    val recipeState = remember { RecipeState() }
    val createRecipeState = remember { CreateRecipeState() }

    CustomTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (windowState.currentWindow) {
                WindowsEnum.RECIPES_LIST -> RecipesList(
                    database = database,
                    windowState = windowState,
                    recipeState = recipeState
                )
                WindowsEnum.RECIPE_DETAIL -> RecipeDetail(
                    database = database,
                    windowState = windowState,
                    recipeState = recipeState,
                    edamamClient = edamamClient
                )
                WindowsEnum.CART -> MainHeader(
                    windowState = windowState
                )
                WindowsEnum.CREATE_RECIPE -> CreateRecipeForm(
                    windowState = windowState,
                    recipeState = createRecipeState,
                    edamamClient = edamamClient,
                )
                WindowsEnum.CHOOSE_RECIPE -> ChooseRecipe(
                    windowState = windowState,
                    edamamClient = edamamClient,
                    recipeState = createRecipeState,
                )
            }
        }
    }
}
