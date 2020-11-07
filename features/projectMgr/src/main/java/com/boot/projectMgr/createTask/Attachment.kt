package com.boot.projectMgr.createTask

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.boot.projectMgr.ui.primaryGreen
import dev.chrisbanes.accompanist.coil.CoilImage

class Attachment(
	val name: String,
	val preview: String,
	val size: Int,
) {
	
	val progress: Float get() = 0.8f
}

val mockAttachment = Attachment(
	name = "Reference_1",
	preview = "https://i.pravatar.cc/200?img=5",
	size = 168
)

@Composable
fun AttachmentProgress(attachment: Attachment) {
	Row {
		CoilImage(
			modifier = Modifier
				.size(48.dp)
				.clip(CircleShape)
				.background(Color.White),
			data = attachment.preview
		)
		Spacer(modifier = Modifier.size(8.dp))
		Column(Modifier.weight(1f)) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(attachment.name)
				Text("${attachment.size} KB")
			}
			LinearProgressIndicator(
				modifier = Modifier.fillMaxWidth(),
				progress = attachment.progress,
				color = primaryGreen
			)
		}
		IconButton(onClick = {}) {
			Icon(Icons.Default.Stop)
		}
	}
}

