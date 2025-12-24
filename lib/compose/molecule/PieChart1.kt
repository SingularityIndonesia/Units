package molecule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
import utils.donout

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
        val thickness = radius * state.thicknessPercent / 100f / state.items.size

        rotate(state.startAngleDeg) {
            translate(center.x, center.y) {
                state.items.forEachIndexed { i, e ->
                    val progress = (e.value * 360 / state.maxValue) *
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

// region Example
private fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Units",
    ) {
        PreviewPieChart1Animated()
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
fun PreviewPieChart1Animated() {
    val state = remember {
        PieChartState(
            maxValue = 100f,
            items = exampleChartItems
        )
    }

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
    val state = remember {
        PieChartState(
            maxValue = 120f,
            thicknessPercent = 30f,
            items = exampleChartItems
        )
    }

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

// endregion