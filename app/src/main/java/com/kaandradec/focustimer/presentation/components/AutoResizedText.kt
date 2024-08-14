package com.kaandradec.focustimer.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.kaandradec.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.displayLarge,
) {
    val timeTextStyle by remember { mutableStateOf(textStyle) }
    val fontSizeFactor = 0.95
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        softWrap = false, // Evitar que se quebre la línea
        style = timeTextStyle,
        onTextLayout = { result ->
            // Ajustar el tamaño de la fuente según el ancho del texto
            // El texto se ira reduciendo hasta que se ajuste
            // esto se hace gracias a que se vuelve a redibujar cada vez que cambia  el estado timeTextStyle
            if (result.didOverflowWidth) {
                val newStyle =
                    timeTextStyle.copy(fontSize = timeTextStyle.fontSize * fontSizeFactor)
            }
        }

    )
}

// Anotación para generar una vista previa de la función
@Preview(
    name = "AutoResizedTextPreview",
    showBackground = true
)

// Composable por preview
@Composable
fun AutoResizedTextPreview() {
    // Tema
    FocusTimerTheme {
        // Composable
        AutoResizedText(text = "Focus Timer")
    }
}
