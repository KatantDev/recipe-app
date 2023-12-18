package org.katant.recipe.features

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.katant.recipe.dtos.TranslationRequestDto
import org.katant.recipe.dtos.TranslationResponseDto

class YandexTranslate (
    private var client: HttpClient
) {
    private var baseUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate"
    private val apiKey = System.getenv("YANDEX_API_KEY") ?: ""
    private val folderId = System.getenv("YANDEX_FOLDER_ID") ?: ""

    suspend fun translateText(
        source: List<String>,
        sourceLanguageCode: String,
        targetLanguageCode: String
    ): TranslationResponseDto {
        val response: HttpResponse = client.request(baseUrl) {
            method = HttpMethod.Post
            headers {
                append(HttpHeaders.Authorization, "Api-Key $apiKey")
            }
            contentType(ContentType.Application.Json)
            setBody(TranslationRequestDto(folderId, source, sourceLanguageCode, targetLanguageCode))
        }
        return response.body()
    }
}