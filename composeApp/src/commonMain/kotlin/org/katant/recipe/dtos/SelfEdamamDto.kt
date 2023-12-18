package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SelfEdamamDto(
    val href: String,
    val title: String
)
