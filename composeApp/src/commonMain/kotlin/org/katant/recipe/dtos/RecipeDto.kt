package org.katant.recipe.dtos

import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum
import org.katant.recipe.store.ImageState

data class RecipeDto(
    val id: Long,
    val name: String,
    val category: RecipeCategoryEnum,
    val photo: ImageState,
    val calorieContent: Long,
    val difficulty: RecipeDifficultyEnum,
)