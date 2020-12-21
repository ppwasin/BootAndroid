package com.boot.parallaxapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ItemCard(
    item: Item,
    scrollX: Float,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.size(width = 256.dp, height = 500.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CoilImage(
                data = item.imageId,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.8f),
                alignment = BiasAlignment((scrollX / 2000f), 0f)
            )
            //Below load image is lag
//            loadVectorResource(id = item.imageId).resource.resource?.let {
//                Image(
//                    imageVector = it,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.8f),
//
//                    // [1]
//                alignment = BiasAlignment((scrollX / 3000f), 0f)
//                )
//            }


            Text(text = item.title)

            Text(text = item.subtitle)
        }
    }
}