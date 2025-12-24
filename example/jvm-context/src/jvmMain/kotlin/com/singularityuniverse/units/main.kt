package com.singularityuniverse.units

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import molecule.PreviewPieChart1
import molecule.PreviewPieChart1Animated

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Units",
    ) {
        PreviewPieChart1Animated()
    }
}