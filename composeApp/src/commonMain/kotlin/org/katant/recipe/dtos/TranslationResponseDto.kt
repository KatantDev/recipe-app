package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TranslationResponseDto (
    val translations: List<TranslationDto>
)