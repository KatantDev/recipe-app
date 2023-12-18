package org.katant.recipe.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TranslationRequestDto(
    val folderId: String,
    val texts: List<String>,
    val sourceLanguageCode: String,
    val targetLanguageCode: String,
)
