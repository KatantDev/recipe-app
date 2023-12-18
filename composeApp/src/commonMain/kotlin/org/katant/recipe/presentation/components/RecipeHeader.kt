package org.katant.recipe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.dtos.RecipeDto
import org.katant.recipe.enums.WindowsEnum
import org.katant.recipe.store.RecipeState
import org.katant.recipe.store.WindowState

@Composable
fun RecipeHeader(
    recipe: RecipeDto,
    recipeState: RecipeState,
    windowState: WindowState,
) {
    val cardModifier = if (!recipeState.isCurrent(recipe)) Modifier.clickable {
        recipeState.updateRecipe(recipe)
        windowState.updateWindow(WindowsEnum.RECIPE_DETAIL)
    } else Modifier

    Card(
        modifier = cardModifier.padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = recipe.category.value,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Image(
                painter = recipe.photo.getPainter(),
                contentDescription = recipe.name,
                modifier = Modifier.padding(bottom = 16.dp).height(200.dp).align(Alignment.CenterHorizontally)
            )
            Column {
                Text(
                    text = "Калории: ${recipe.calorieContent}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Сложность: ${recipe.difficulty.value}",
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}