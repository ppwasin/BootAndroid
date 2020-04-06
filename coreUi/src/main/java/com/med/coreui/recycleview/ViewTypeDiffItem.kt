package com.med.coreui.recycleview

interface ViewTypeDiffItem : ViewType, DiffItem

interface ViewType {
	fun getViewType(): Int
}