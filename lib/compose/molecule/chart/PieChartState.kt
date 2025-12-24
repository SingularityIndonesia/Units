package molecule.chart

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import molecule.chart.ChartItem

data class PieChartState(
    @Stable
    val items: List<ChartItem> = emptyList(),
    val startAngleDeg: Float = 0f,
    val preferredThickness: Dp = 10.dp,
)