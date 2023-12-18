package org.katant.recipe.presentation.windows

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.katant.recipe.Database
import org.katant.recipe.enums.WindowsEnum
import org.katant.recipe.features.EdamamClient
import org.katant.recipe.presentation.components.RecipeBody
import org.katant.recipe.presentation.components.RecipeHeader
import org.katant.recipe.presentation.components.RecipeIngredients
import org.katant.recipe.store.RecipeState
import org.katant.recipe.store.WindowState


@Composable
fun RecipeDetail(database: Database, windowState: WindowState, recipeState: RecipeState, edamamClient: EdamamClient) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(horizontal = 30.dp),
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).widthIn(256.dp, 768.dp),
            horizontalAlignment = Alignment.Start
        ) {
            RecipeHeader(
                recipe = recipeState.recipe!!,
                recipeState = recipeState,
                windowState = windowState,
            )
            RecipeIngredients(
                database = database,
                recipeState = recipeState,
            )
            RecipeBody(database = database, recipeState = recipeState)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Column {
            IconButton(
                onClick = {
                    recipeState.reset()
                    windowState.reset()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onBackground
                )
            }
            IconButton(
                onClick = {
                    database.recipeQueries.deleteById(id = recipeState.recipe!!.id)
                    recipeState.reset()
                    windowState.reset()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
