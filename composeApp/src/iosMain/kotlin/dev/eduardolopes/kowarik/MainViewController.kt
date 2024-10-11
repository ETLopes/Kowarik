package dev.eduardolopes.kowarik

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import dev.eduardolopes.kowarik.networking.KowarikClient
import dev.eduardolopes.kowarik.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController { App(client = remember { KowarikClient(
    createHttpClient(Darwin.create())
) }) }