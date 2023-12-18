package org.katant.recipe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import org.katant.recipe.store.RecipeBodyTextState

@Composable
fun RecipeBodyTextBlock(
    recipeBodyTextState: RecipeBodyTextState,
) {
    if (recipeBodyTextState.isEditable) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = recipeBodyTextState.value,
                modifier = Modifier.padding(bottom = 8.dp).weight(1f),
                textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary),
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
                onValueChange = { recipeBodyTextState.value = it },
            )
            Icon(
                Icons.Default.Done,
                contentDescription = "Сохранить",
                modifier = Modifier.clickable {
                    recipeBodyTextState.saveChanges()
                }
            )
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = recipeBodyTextState.value,
                modifier = Modifier.padding(bottom = 8.dp).weight(2f),
                style = MaterialTheme.typography.body1,
            )
            Row {
                Column {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Изменить",
                        modifier = Modifier.clickable { recipeBodyTextState.isEditable = true }
                    )
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Удалить",
                        modifier = Modifier.clickable {
                            recipeBodyTextState.delete()
                        }
                    )
                }
                ChangePositionButtons(recipeBodyState = recipeBodyTextState)
            }

        }
    }
}
