package org.katant.recipe.dtos

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import org.katant.recipe.enums.RecipeCategoryEnum
import org.katant.recipe.enums.RecipeDifficultyEnum
import org.katant.recipe.store.ImageState

@Parcelize
data class CreateRecipeDto (
    var name: String = "",
    var category: RecipeCategoryEnum = RecipeCategoryEnum.SOUP,
    val photo: ImageState = ImageState(),
    var calorieContent: Double = 0.0,
    var difficulty: RecipeDifficultyEnum = RecipeDifficultyEnum.EASY,
): Parcelable