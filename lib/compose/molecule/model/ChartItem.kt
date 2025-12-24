package molecule.model

import androidx.compose.ui.graphics.Color

data class ChartItem(
    val id: String,
    val value: Float,
    val label: String,
    val color: Color
)