package org.katant.recipe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.katant.recipe.db.DriverFactory
import org.katant.recipe.presentation.App


fun main() = application {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true } )
        }
    }
    Window(
        onCloseRequest = {
            exitApplication()
            client.close()
        },
        title = "Рецепты"
    ) {
        App(DriverFactory().createDriver(), client)
    }
}
