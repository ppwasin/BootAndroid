package com.boot.parallaxapp

data class Item(val imageId: Int, val title: String, val subtitle: String)

val imagedIds = listOf(
    R.drawable.ic_circuit_primary,
    R.drawable.ic_cloudy,
    R.drawable.ic_icon_grid,
    R.drawable.ic_meteor,
    R.drawable.ic_sound_wave,
    R.drawable.ic_launcher_foreground
)
val items = generateItems(5)


fun generateItems(totalItems: Int): List<Item> =
    (0..totalItems).map {
        Item(
            title = "Item #$it",
            subtitle = "Subtitle",
            imageId = imagedIds[it]
        )
    }


fun imageUrlForSize(size: Int, id: Int): String = "https://i.pravatar.cc/$size?img=$id"