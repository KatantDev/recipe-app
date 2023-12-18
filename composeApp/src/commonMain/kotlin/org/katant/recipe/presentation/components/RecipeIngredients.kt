package org.katant.recipe.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import org.katant.recipe.store.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.Database


@Composable
fun RecipeIngredients(database: Database, recipeState: RecipeState) {
    val ingredientsState = remember { mutableStateListOf<RecipeIngredientState>() }
    for (recipeBody in database.recipeIngredientQueries.selectByRecipeId(
        recipeId = recipeState.recipe!!.id
    ).executeAsList()) {
        RecipeIngredientState(
            id = recipeBody.id,
            foodName = recipeBody.foodName,
            measure = recipeBody.measure,
            quantity = recipeBody.quantity,
            database = database,
            ingredientsState = ingredientsState,
        )
    }

    Card(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight(),
        ) {
            Text(
                text = "Ингридиенты",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
            )
            ingredientsState.forEach { state ->
                RecipeIngredientBlock(ingredientState = state)
            }
            Button (
                modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally),
                onClick = {
                    createIngredientState(
                        database = database,
                        ingredientsState = ingredientsState,
                        recipeId = recipeState.recipe!!.id,
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить ингридиент"
                )
                Text("Добавить ингридиент")
            }
        }
    }
}