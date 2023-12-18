package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RecipeEdamamDto (
    var label: String,
    var ingredientLines: List<String>,
    val ingredients: List<IngredientEdamamDto>,
    val calories: Double,
    var instructionLines: List<String>,
)