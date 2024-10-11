package dev.eduardolopes.kowarik

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eduardolopes.kowarik.networking.KowarikClient
import dev.eduardolopes.kowarik.networking.Vendors
import dev.eduardolopes.kowarik.util.onError
import dev.eduardolopes.kowarik.util.onSuccess
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(client: KowarikClient) {
    MaterialTheme {
        var vendors by remember { mutableStateOf<Vendors?>(null) }
        var isLoading by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                isLoading = true
                val response =
                    client.getKowarikVendors().onSuccess { vendors = it }.onError {
                        errorMessage = it.name
                    }
                isLoading = false
            }

        }
        Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Vendor", fontSize = 24.sp)

            // card with vendor info
            if (isLoading) {
                CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = MaterialTheme.colors.primary,
                )
            } else {
                vendors?.vendors?.map {
                    Text(text = it.name, fontSize = 20.sp)
                    Text(text = it.description, fontSize = 16.sp)
                    Text(text = it.phone, fontSize = 16.sp)
                    Text(text = it.address, fontSize = 16.sp)
                }
                    ?: Text(text = errorMessage, fontSize = 16.sp)
            }
        }
    }
}
