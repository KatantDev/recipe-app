package org.katant.recipe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum
import org.katant.recipe.store.FiltersState

@Composable
fun FilterButtons(
    filtersState: FiltersState,
) {
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedDifficulty by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box (modifier = Modifier.padding(end = 10.dp)) {
                TextField(
                    value = filtersState.difficulty?.value ?: "Не выбрано",
                    enabled = false,
                    onValueChange = { },
                    label = { Text("Сложность") },
                    modifier = Modifier.clickable { expandedDifficulty = true }
                )
                DropdownMenu(
                    expanded = expandedDifficulty,
                    onDismissRequest = { expandedDifficulty = false }
                ) {
                    DropdownMenuItem(onClick = {
                        filtersState.updateDifficulty(null)
                        expandedDifficulty = false
                    }) {
                        Text("Не выбрано")
                    }
                    RecipeDifficultyEnum.entries.forEach {
                        DropdownMenuItem(onClick = {
                            filtersState.updateDifficulty(it)
                            expandedDifficulty = false
                        }) {
                            Text(it.value)
                        }
                    }
                }
            }
            Box {
                TextField(
                    value = filtersState.category?.value ?: "Не выбрано",
                    enabled = false,
                    onValueChange = { },
                    label = { Text("Категория") },
                    modifier = Modifier.clickable { expandedCategory = true }
                )
                DropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false }
                ) {
                    DropdownMenuItem(onClick = {
                        filtersState.updateCategory(null)
                        expandedCategory = false
                    }) {
                        Text("Не выбрано")
                    }
                    RecipeCategoryEnum.entries.forEach {
                        DropdownMenuItem(onClick = {
                            filtersState.updateCategory(it)
                            expandedCategory = false
                        }) {
                            Text(it.value)
                        }
                    }
                }
            }
        }
    }
}