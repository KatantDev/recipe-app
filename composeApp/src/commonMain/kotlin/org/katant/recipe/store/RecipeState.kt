package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.katant.recipe.dtos.RecipeDto

class RecipeState {
    var recipe by mutableStateOf<RecipeDto?>(null)
        private set

    fun updateRecipe(newRecipe: RecipeDto) {
        recipe = newRecipe
    }

    fun isCurrent(currentRecipe: RecipeDto): Boolean {
        return recipe === currentRecipe
    }

    fun reset() {
        recipe = null
    }
}