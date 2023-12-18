package org.katant.recipe.presentation.windows

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.katant.recipe.Database
import org.katant.recipe.enums.LoadingEdamamStatus
import org.katant.recipe.enums.WindowsEnum
import org.katant.recipe.features.EdamamClient
import org.katant.recipe.store.CreateRecipeState
import org.katant.recipe.store.WindowState

@Composable
fun ChooseRecipe(
    windowState: WindowState,
    edamamClient: EdamamClient,
    recipeState: CreateRecipeState,
) {
    if (edamamClient.loadingStatus != LoadingEdamamStatus.LOADED) {
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 25.dp, end = 25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 10.dp))
            Text(text = edamamClient.loadingStatus.value, style = MaterialTheme.typography.h6)
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 25.dp, end = 25.dp).verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Выберите рецепт",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            )
            edamamClient.recipesResponse!!.hits.forEach { hit ->
                TextButton(
                    onClick = {
                        edamamClient.scope.launch {
                            edamamClient.createRecipe(recipeState, hit.recipe)
                            windowState.updateWindow(WindowsEnum.RECIPES_LIST)
                        }
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = hit.recipe.label, modifier = Modifier.padding(end = 4.dp))
                        if (hit.recipe.instructionLines.isEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Рецепт без инструкции",
                            )
                        }
                    }
                }
            }
        }
    }
    Column(modifier = Modifier.padding(4.dp)) {
        IconButton(
            onClick = {
                windowState.updateWindow(WindowsEnum.CREATE_RECIPE)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}