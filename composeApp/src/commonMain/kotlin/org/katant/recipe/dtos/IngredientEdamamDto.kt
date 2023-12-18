package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class IngredientEdamamDto (
    var text: String,
    var quantity: Double? = null,
    var measure: String? = null,
    var food: String,
    var weight: Double,
    var foodCategory: String? = null,
    var foodId: String,
    var image: String? = null,
)