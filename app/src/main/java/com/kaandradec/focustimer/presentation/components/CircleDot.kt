package com.kaandradec.focustimer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kaandradec.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun CircleDot(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Box(// Los elementos se colocan uno encima del otro en el orden en que se agregan
        modifier = modifier
            .size(FocusTimerTheme.dimens.iconSizeSmall)
            .clip(shape = CircleShape)
            .background(color = color)
    )
}

@Preview(showBackground = true)
@Composable
fun CircleDotPreview() {
    FocusTimerTheme {
        CircleDot()
    }
}