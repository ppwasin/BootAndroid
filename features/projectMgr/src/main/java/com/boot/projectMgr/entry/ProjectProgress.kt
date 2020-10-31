package com.boot.projectMgr.entry

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun ProjectProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    status: Status,
) {
    val text = "${(progress * 100).roundToInt()}%"
    val color = status.color
    Box(modifier
        .circularProgress(progress, color)
        .size(48.dp),
        alignment = Alignment.Center
    ) {
        Text(text, color = color, fontWeight = FontWeight.Bold)
    }
}


private val stroke = Stroke(8f, cap = StrokeCap.Butt)
fun Modifier.circularProgress(
    progress: Float,
    primaryColor: Color,
) = this.drawWithCache {
    val sweep = progress * 360
    onDraw {
        drawCircularIndicator(
            startAngle = 270f,
            sweep = sweep,
            color = primaryColor,
            stroke = stroke
        )
    }

}

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke,
) {
    // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
    if (sweep < 360) {
        drawArc(
            color = color.copy(alpha = 0.3f),
            startAngle = (startAngle + sweep) % 360,
            sweepAngle = 360 - sweep,
            useCenter = false,
            topLeft = Offset(diameterOffset, diameterOffset),
            size = Size(arcDimen, arcDimen),
            style = stroke
        )
    }
}