package dev.eduardolopes.kowarik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import dev.eduardolopes.kowarik.networking.KowarikClient
import dev.eduardolopes.kowarik.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                client = remember { KowarikClient(createHttpClient(OkHttp.create())) }
            )
        }
    }
}