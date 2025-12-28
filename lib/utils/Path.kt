package utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType

fun donut(
    outerRadius: Float,
    thickness: Float,
    sweepAngle: Float,
): Path {
    val innerRadius = outerRadius - thickness

    val path = Path().apply {
        fillType = PathFillType.EvenOdd

        // Outer arc
        arcTo(
            rect = Rect(
                center = Offset.Zero,
                radius = outerRadius
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = sweepAngle,
            forceMoveTo = false
        )

        // Inner arc (reverse direction)
        arcTo(
            rect = Rect(
                center = Offset.Zero,
                radius = innerRadius
            ),
            startAngleDegrees = sweepAngle,
            sweepAngleDegrees = -sweepAngle,
            forceMoveTo = false
        )

        close()
    }

    return path
}