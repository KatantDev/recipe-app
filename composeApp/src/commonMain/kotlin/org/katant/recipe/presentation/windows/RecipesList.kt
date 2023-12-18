package org.katant.recipe.presentation.windows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.Database
import org.katant.recipe.dtos.RecipeDto
import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum
import org.katant.recipe.presentation.components.CreateRecipeCard
import org.katant.recipe.presentation.components.FilterButtons
import org.katant.recipe.presentation.components.MainHeader
import org.katant.recipe.presentation.components.RecipeHeader
import org.katant.recipe.store.FiltersState
import org.katant.recipe.store.ImageState
import org.katant.recipe.store.RecipeState
import org.katant.recipe.store.WindowState

@Composable
fun RecipesList(database: Database, windowState: WindowState, recipeState: RecipeState) {
    val recipes = mutableListOf<RecipeDto>()
    val filtersState = remember { FiltersState() }

    for (recipe in database.recipeQueries.selectAll(
        categorySearch = filtersState.category?.value,
        difficultySearch = filtersState.difficulty?.value
    ).executeAsList()) {
        recipes.add(
            RecipeDto(
                id = recipe.id,
                name = recipe.name,
                calorieContent = recipe.calorieContent,
                category = RecipeCategoryEnum.from(recipe.category)!!,
                difficulty = RecipeDifficultyEnum.from(recipe.difficulty)!!,
                photo = ImageState(path = recipe.photoPath)
            )
        )
    }


    Column {
        MainHeader(windowState = windowState)
        FilterButtons(filtersState = filtersState)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(320.dp),
            modifier = Modifier.weight(1f),
        ) {
            items(recipes) { recipe ->
                RecipeHeader(
                    recipe = recipe,
                    recipeState = recipeState,
                    windowState = windowState,
                )
            }
            item {
                CreateRecipeCard(windowState = windowState)
            }
        }
    }
}