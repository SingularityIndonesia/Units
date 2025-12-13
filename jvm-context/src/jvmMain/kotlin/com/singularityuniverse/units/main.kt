package com.singularityuniverse.units

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import molecule.PhoneNumberInput1Preview

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Units",
    ) {
        PhoneNumberInput1Preview()
    }
}