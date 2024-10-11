package dev.eduardolopes.kowarik

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.eduardolopes.kowarik.networking.KowarikClient
import dev.eduardolopes.kowarik.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kowarik",
    ) {
        App(client = remember { KowarikClient(createHttpClient(OkHttp.create())) })
    }
}