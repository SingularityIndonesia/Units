package molecule.model

import androidx.compose.ui.graphics.Color

data class ChartItem(
    val id: String,
    val valuePercent: Float,
    val label: String,
    val color: Color
)