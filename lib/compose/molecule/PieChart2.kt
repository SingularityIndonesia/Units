package molecule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import animator.ProgressValueAnimator
import molecule.model.ChartItem
import molecule.model.PieChartState
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.LocalProgressAnimatorValue
import utils.donut

@Composable
fun PieChart2(
    modifier: Modifier = Modifier,
    state: PieChartState = remember { PieChartState() }
) {
    val progressAnimator = LocalProgressAnimatorValue.current
    val total: Float by derivedStateOf { state.items.sumOf { it.value.toDouble() }.toFloat() }

    Canvas(
        modifier = modifier
    ) {
        val radius = ((size.height.takeIf { it >= size.width } ?: size.width) / 2f)

        state.items.forEachIndexed { i, e ->
            val progressDeg = (e.value * 360 / total) *
                    99.99f / 100f * // so the chart won't collapse to 100%
                    progressAnimator / 100f

            val startAngel = (state.items.take(i).map { it.value }.sum() / total * 360f) * progressAnimator / 100f
            rotate(startAngel ) {
                translate(center.x, center.y) {
                    drawPath(
                        path = donut(
                            outerRadius = radius,
                            thickness = state.thicknessPercent / 100f * radius,
                            sweepAngle = progressDeg,
                        ),
                        color = e.color
                    )
                }
            }
        }
    }
}

// region Example
private fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Units",
    ) {
        PreviewPieChart2Animated()
    }
}

private val exampleChartItems = listOf(
    ChartItem(
        id = "1",
        value = 90f,
        label = "Item 1",
        color = Color.Red,
    ),
    ChartItem(
        id = "2",
        value = 50f,
        label = "Item 2",
        color = Color.Blue,
    ),
    ChartItem(
        id = "3",
        value = 30f,
        label = "Item 3",
        color = Color.Yellow,
    ),
    ChartItem(
        id = "4",
        value = 70f,
        label = "Item 4",
        color = Color.Gray,
    ),
    ChartItem(
        id = "5",
        value = 100f,
        label = "Item 5",
        color = Color.Cyan,
    )
)

@Preview
@Composable
fun PreviewPieChart2Animated() {
    val state = remember { PieChartState(exampleChartItems) }

    ProgressValueAnimator {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PieChart2(
                modifier = Modifier
                    .size(300.dp)
                    .border(1.dp, Color.Black),
                state = state
            )
        }
    }
}

@Preview
@Composable
fun PreviewPieChart2() {
    val state = remember {
        PieChartState(
            maxValue = 120f,
            items = exampleChartItems
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PieChart2(
            modifier = Modifier
                .size(300.dp)
                .border(1.dp, Color.Black),
            state = state
        )
    }
}

// endregion