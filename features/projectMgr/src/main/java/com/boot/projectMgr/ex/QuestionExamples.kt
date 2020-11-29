package com.boot.projectMgr.ex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.snapshotFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.sqrt

//Question
class Item

@Composable fun Example(itemFlow: StateFlow<Item>) {
	val item by itemFlow.collectAsState()
	//	val itemState = itemFlow.collectAsState()
	//	val item2 = itemState.value
}

//val item by remember { mutableStateOf<Item?>(null) }
//
////Convert to Flow: pass lambda function that will read item
//val itemFlow = snapshotFlow { item }

@ExperimentalComposeApi
@Composable fun Example() {
	val x by remember { mutableStateOf(0) }
	val y by remember { mutableStateOf(0) }
	
	val distFlow = snapshotFlow { sqrt((x * x * y * y).toDouble()) }
	//	val dist = derivedStateOf {  sqrt((x * x * y * y).toDouble()) }
	val dist = sqrt((x * x * y * y).toDouble())
}