package molecule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import animator.ProgressValueAnimator
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.LocalProgressAnimatorValue
import utils.donout

private val exampleChartItems = listOf(
    ChartItem(
        id = "1",
        valuePercent = 90f,
        label = "Item 1",
        color = Color.Red,
    ),
    ChartItem(
        id = "2",
        valuePercent = 50f,
        label = "Item 2",
        color = Color.Blue,
    ),
    ChartItem(
        id = "3",
        valuePercent = 30f,
        label = "Item 3",
        color = Color.Yellow,
    ),
    ChartItem(
        id = "4",
        valuePercent = 70f,
        label = "Item 4",
        color = Color.Gray,
    ),
    ChartItem(
        id = "5",
        valuePercent = 100f,
        label = "Item 5",
        color = Color.Cyan,
    )
)

@Preview
@Composable
fun PreviewPieChart1Animated() {
    val state = remember { PieChartState(exampleChartItems) }

    ProgressValueAnimator {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PieChart1(
                modifier = Modifier
                    .size(100.dp)
                    .border(1.dp, Color.Black),
                state = state
            )
        }
    }
}

@Preview
@Composable
fun PreviewPieChart1() {
    val state = remember { PieChartState(exampleChartItems) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PieChart1(
            modifier = Modifier
                .size(100.dp)
                .border(1.dp, Color.Black),
            state = state
        )
    }
}

data class PieChartState(
    @Stable
    val items: List<ChartItem> = emptyList(),
    val startAngleDeg: Float = 0f,
    val preferredThickness: Dp = 10.dp,
)

data class ChartItem(
    val id: String,
    val valuePercent: Float,
    val label: String,
    val color: Color
)

@Composable
fun PieChart1(
    modifier: Modifier = Modifier,
    state: PieChartState = remember { PieChartState() }
) {
    val progressAnimator = LocalProgressAnimatorValue.current

    Canvas(
        modifier = modifier
    ) {
        val radius = ((size.height.takeIf { it >= size.width } ?: size.width) / 2f)
        val thickness = radius * 50 / 100f / state.items.size

        rotate(state.startAngleDeg) {
            translate(center.x, center.y) {
                state.items.forEachIndexed { i, e ->
                    val progress = (e.valuePercent * 360 / 100f) *
                            99.99f / 100f * // so the chart won't collapse to 100%
                            progressAnimator / 100f

                    drawPath(
                        path = donout(
                            outerRadius = radius - (i * thickness),
                            thickness = thickness,
                            sweepAngle = progress,
                        ),
                        color = e.color
                    )
                }
            }
        }
    }
}