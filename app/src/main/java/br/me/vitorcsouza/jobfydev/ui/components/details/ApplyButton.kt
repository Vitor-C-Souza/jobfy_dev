package br.me.vitorcsouza.jobfydev.ui.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme

@Composable
fun ApplyButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0B3C5D),
            Color(0xFF165A8F)
        )
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(gradientBrush),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        contentPadding = ButtonDefaults.ContentPadding,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        )
    ) {
        Text(
            text = "Apply Now",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ApplyButtonPreview() {
    JobfyDevTheme {
        ApplyButton()
    }
}