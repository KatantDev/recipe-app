package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SearchEdamamDto(
    val from: Int,
    val to: Int,
    val count: Int,
    var hits: List<HitEdamamDto>,
)
