package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class HitEdamamDto(
    val recipe: RecipeEdamamDto,
    val _links: LinksEdamamDto,
)
