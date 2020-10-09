package com.boot.compose

object nav_graph {

    const val id = 1 // graph id

    object dest {
        const val home = 2
        const val plant_detail = 3
    }

    object action {
        const val to_plant_detail = 4
    }

    object args {
        const val plant_id = "plantId"
    }
}