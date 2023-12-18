package org.katant.recipe.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.store.RecipeBodyImageState

@Composable
fun RecipeBodyImageBlock(
    recipeBodyImageState: RecipeBodyImageState
) {
    if (recipeBodyImageState.path !== null) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = recipeBodyImageState.getPainter(),
                contentDescription = "Изображение шага",
                modifier = Modifier.weight(1f)
            )
            Row {
                Column {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Изменить",
                        modifier = Modifier.clickable { recipeBodyImageState.selectImage() }
                    )
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Удалить",
                        modifier = Modifier.clickable { recipeBodyImageState.delete() }
                    )
                }
                ChangePositionButtons(recipeBodyState = recipeBodyImageState)
            }
        }
    } else {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = recipeBodyImageState::selectImage) {
                Icon(
                    Icons.Default.Search,
                    modifier = Modifier.padding(end = 4.dp),
                    contentDescription = "Выбрать изображение",
                )
                Text("Выбрать изображение")
            }
            Row {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Удалить",
                    modifier = Modifier.clickable { recipeBodyImageState.delete() }
                )
            }
        }
    }
}
