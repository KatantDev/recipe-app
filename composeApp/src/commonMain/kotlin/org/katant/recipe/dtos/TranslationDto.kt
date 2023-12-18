package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TranslationDto(
    val text: String = "",
)
