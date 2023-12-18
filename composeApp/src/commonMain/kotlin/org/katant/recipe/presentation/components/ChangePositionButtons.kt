package org.katant.recipe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.katant.recipe.interfaces.RecipeBodyStateInterface

@Composable
fun ChangePositionButtons(
    recipeBodyState: RecipeBodyStateInterface
) {
    Column {
        Icon(
            Icons.Default.KeyboardArrowUp,
            contentDescription = "Наверх",
            modifier = Modifier.clickable {
                val currentIndex = recipeBodyState.recipeBodyState.indexOf(recipeBodyState)
                if (currentIndex > 0) {
                    val previousElement = recipeBodyState.recipeBodyState[currentIndex - 1]
                    recipeBodyState.changePositionTo(previousElement.id, currentIndex, currentIndex - 1)
                }
            }
        )
        Icon(
            Icons.Default.KeyboardArrowDown,
            contentDescription = "Вниз",
            modifier = Modifier.clickable {
                val currentIndex = recipeBodyState.recipeBodyState.indexOf(recipeBodyState)
                if (currentIndex < recipeBodyState.recipeBodyState.size - 1) {
                    val nextElement = recipeBodyState.recipeBodyState[currentIndex + 1]
                    recipeBodyState.changePositionTo(nextElement.id, currentIndex, currentIndex + 1)
                }
            }
        )
    }
}