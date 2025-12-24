package animator

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import utils.LocalProgressAnimatorValue

/**
 * If the content supports `LocalProgressAnimatorValue`
 */
@Composable
fun ProgressValueAnimator(
    animationSpec: AnimationSpec<Float> = tween(
        durationMillis = 2000,
        easing = FastOutSlowInEasing
    ),
    content: @Composable () -> Unit
) {
    val isAnimationStarted = remember { mutableStateOf(false) }
    val isAnimated = remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (isAnimated.value) 100f else 0f,
        animationSpec = animationSpec,
        label = "ProgressValueAnimator"
    )

    LaunchedEffect(
        isAnimationStarted.value,
        isAnimated.value
    ) {
        if (!isAnimationStarted.value && !isAnimated.value) {
            isAnimationStarted.value = true
            isAnimated.value = true
        }
    }

    CompositionLocalProvider(LocalProgressAnimatorValue provides progress) {
        content.invoke()
    }
}