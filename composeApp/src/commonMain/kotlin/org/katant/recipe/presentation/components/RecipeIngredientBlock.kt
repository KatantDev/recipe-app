package org.katant.recipe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.katant.recipe.store.RecipeIngredientState

@Composable
fun RecipeIngredientBlock(
    ingredientState: RecipeIngredientState,
) {
    if (ingredientState.isEditable) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = ingredientState.foodName,
                modifier = Modifier.padding(bottom = 8.dp).weight(2f),
                textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary),
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
                onValueChange = { ingredientState.foodName = it },
            )
            Row (
                modifier = Modifier.padding(bottom = 8.dp).weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    BasicTextField(
                        modifier = Modifier.width(IntrinsicSize.Min),
                        value = ingredientState.quantity?.toString() ?: "",
                        textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary).copy(textAlign = TextAlign.Center),
                        cursorBrush = SolidColor(MaterialTheme.colors.primary),
                        onValueChange = {
                            if (it == "") {
                                ingredientState.quantity = null
                                return@BasicTextField
                            }
                            val parsedValue = it.toDoubleOrNull()
                            if (it.length < 10) {
                                ingredientState.quantity = parsedValue
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    BasicTextField(
                        modifier = Modifier.padding(bottom = 8.dp),
                        value = ingredientState.measure,
                        textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary, textAlign = TextAlign.Center),
                        cursorBrush = SolidColor(MaterialTheme.colors.primary),
                        onValueChange = {
                            ingredientState.measure = it
                        },
                    )
                }
                Icon(
                    Icons.Default.Done,
                    contentDescription = "Сохранить",
                    modifier = Modifier.clickable {
                        ingredientState.saveChanges()
                    }
                )
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = ingredientState.foodName,
                modifier = Modifier.padding(bottom = 8.dp).weight(2f),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Row (
                modifier = Modifier.padding(bottom = 8.dp).weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column (
                    modifier = Modifier.padding(end = 4.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = ingredientState.quantity?.toString() ?: "",
                        style = MaterialTheme.typography.body1,
                    )
                    Text(
                        text = ingredientState.measure,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Row (modifier = Modifier) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Изменить",
                        modifier = Modifier.clickable { ingredientState.isEditable = true }
                    )
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Удалить",
                        modifier = Modifier.clickable {
                            ingredientState.delete()
                        }
                    )
                }
            }
        }
    }
}
