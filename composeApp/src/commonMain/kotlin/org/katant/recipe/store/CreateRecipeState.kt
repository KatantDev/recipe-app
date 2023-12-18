package org.katant.recipe.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum

class CreateRecipeState {
    var name by mutableStateOf("")
    val photo by mutableStateOf(ImageState())

    var expandedDifficulty by mutableStateOf(false)
    var difficulty by mutableStateOf(RecipeDifficultyEnum.EASY)

    var expandedCategory by mutableStateOf(false)
    var category by mutableStateOf(RecipeCategoryEnum.SOUP)

    var errorMessage by mutableStateOf("")

    fun reset() {
        name = ""
        photo.path = null
        difficulty = RecipeDifficultyEnum.EASY
        category = RecipeCategoryEnum.SOUP
        errorMessage = ""
    }

    fun validate(): Boolean {
        if (name.isEmpty()) {
            errorMessage = "Ключевые слова рецепта не могут быть пустыми"
            return false
        }
        if (photo.path == null) {
            errorMessage = "Фотография не может быть пустой"
            return false
        }
        return true
    }
}