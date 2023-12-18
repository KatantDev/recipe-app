package org.katant.recipe.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.katant.recipe.Database
import org.katant.recipe.interfaces.RecipeBodyStateInterface
import org.katant.recipe.store.RecipeBodyImageState
import org.katant.recipe.store.RecipeBodyTextState
import org.katant.recipe.store.RecipeState


@Composable
fun RecipeBody(database: Database, recipeState: RecipeState) {
    val recipeBodyState = remember { mutableStateListOf<RecipeBodyStateInterface>() }
    for (recipeBody in database.recipeBodyQueries.selectByRecipeId(
        recipeId = recipeState.recipe!!.id
    ).executeAsList()) {
        if (recipeBody.type == "image") {
            RecipeBodyImageState(
                id = recipeBody.id,
                path = recipeBody.body,
                database = database,
                recipeBodyState = recipeBodyState,
            )
        } else {
            RecipeBodyTextState(
                id = recipeBody.id,
                value = recipeBody.body ?: "",
                database = database,
                recipeBodyState = recipeBodyState,
            )
        }
    }

    Card(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight(),
        ) {
            Text(
                text = "Инструкция по приготовлению",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
            )
            recipeBodyState.forEach { state ->
                if (state is RecipeBodyTextState) RecipeBodyTextBlock(recipeBodyTextState = state)
                if (state is RecipeBodyImageState) RecipeBodyImageBlock(recipeBodyImageState = state)
            }
            RecipeBodyBottomButtons(
                recipeId = recipeState.recipe!!.id,
                database = database,
                recipeBodyState = recipeBodyState,
            )
        }
    }
}