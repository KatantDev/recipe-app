package org.katant.recipe.presentation.windows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum
import org.katant.recipe.enums.WindowsEnum
import org.katant.recipe.features.EdamamClient
import org.katant.recipe.presentation.components.MainHeader
import org.katant.recipe.store.CreateRecipeState
import org.katant.recipe.store.WindowState

@Composable
fun CreateRecipeForm(
    windowState: WindowState,
    recipeState: CreateRecipeState,
    edamamClient: EdamamClient
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MainHeader(windowState = windowState, createRecipeState = recipeState)
        Column(
            modifier = Modifier.padding(8.dp).width(512.dp).verticalScroll(rememberScrollState()),
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = recipeState.name,
                onValueChange = { recipeState.name = it },
                label = { Text("Ключевые слова рецепта") }
            )
            Box {
                TextField(
                    value = recipeState.category.value,
                    enabled = false,
                    onValueChange = { },
                    label = { Text("Категория") },
                    modifier = Modifier.fillMaxWidth().clickable { recipeState.expandedCategory = true }
                )
                DropdownMenu(
                    expanded = recipeState.expandedCategory,
                    onDismissRequest = { recipeState.expandedCategory = false }
                ) {
                    RecipeCategoryEnum.entries.forEach {
                        DropdownMenuItem(onClick = {
                            recipeState.category = it
                            recipeState.expandedCategory = false
                        }) {
                            Text(it.value)
                        }
                    }
                }
            }
            TextField(
                value = recipeState.photo.path ?: "",
                enabled = false,
                onValueChange = { },
                label = { Text("Фотография") },
                modifier = Modifier.fillMaxWidth().clickable { recipeState.photo.selectImage() }
            )
            Box {
                TextField(
                    value = recipeState.difficulty.value,
                    enabled = false,
                    onValueChange = { },
                    label = { Text("Сложность") },
                    modifier = Modifier.fillMaxWidth().clickable { recipeState.expandedDifficulty = true }
                )
                DropdownMenu(
                    expanded = recipeState.expandedDifficulty,
                    onDismissRequest = { recipeState.expandedDifficulty = false }
                ) {
                    RecipeDifficultyEnum.entries.forEach {
                        DropdownMenuItem(onClick = {
                            recipeState.difficulty = it
                            recipeState.expandedDifficulty = false
                        }) {
                            Text(it.value)
                        }
                    }
                }
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    if (!recipeState.validate()) {
                        return@Button
                    }
                    recipeState.errorMessage = ""
                    edamamClient.scope.launch {
                        edamamClient.getRecipes(query = recipeState.name)
                    }
                    windowState.updateWindow(WindowsEnum.CHOOSE_RECIPE)
                }) {
                Text("Создать рецепт")
            }
            if (recipeState.errorMessage.isNotEmpty()) {
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = recipeState.errorMessage,
                        color = MaterialTheme.colors.error,
                    )
                }
            }
        }
    }
}