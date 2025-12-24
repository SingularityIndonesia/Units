package molecule.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PieChartState(
    @Stable
    val items: List<ChartItem> = emptyList(),
    val startAngleDeg: Float = 0f,
    val preferredThickness: Dp = 10.dp,
    val maxValue: Float = 100f,
)