package org.katant.recipe.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.Database
import org.katant.recipe.interfaces.RecipeBodyStateInterface
import org.katant.recipe.store.createRecipeBodyImageState
import org.katant.recipe.store.createRecipeBodyTextState

@Composable
fun RecipeBodyBottomButtons (
    recipeId: Long,
    database: Database,
    recipeBodyState: SnapshotStateList<RecipeBodyStateInterface>
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
    ) {
        Button(
            onClick = {
                createRecipeBodyImageState(
                    database = database,
                    recipeBodyState = recipeBodyState,
                    recipeId = recipeId,
                )
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить изображение"
            )
            Text("Добавить изображение")
        }
        Button (
            onClick = {
                createRecipeBodyTextState(
                    database = database,
                    recipeBodyState = recipeBodyState,
                    recipeId = recipeId,
                )
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить текст"
            )
            Text("Добавить текст")
        }
    }
}