package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.katant.recipe.dtos.RecipeDto
import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum

class FiltersState {
    var category by mutableStateOf<RecipeCategoryEnum?>(null)
        private set
    var difficulty by mutableStateOf<RecipeDifficultyEnum?>(null)
        private set

    fun updateCategory(newCategory: RecipeCategoryEnum? = null) {
        category = newCategory
    }

    fun updateDifficulty(newDifficulty: RecipeDifficultyEnum? = null) {
        difficulty = newDifficulty
    }
}