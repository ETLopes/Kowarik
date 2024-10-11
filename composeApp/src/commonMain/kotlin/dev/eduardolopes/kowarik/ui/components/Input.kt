package dev.eduardolopes.kowarik.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Input(placeholder: String, label: String, defaultValue: String, onTextChange: (String) -> Unit) {
    var text by remember { mutableStateOf(defaultValue) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onTextChange(it)
        },
        label = { Text(label) },
        placeholder = { Text(placeholder) }
    )
}
