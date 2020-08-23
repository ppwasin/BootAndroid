package com.med.utilization.bnv.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.med.utilization.bnv.data.ArticleService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class HomeViewModel @AssistedInject constructor(
	private val articleService: ArticleService,
	@Assisted private val saveStateHandle: SavedStateHandle
) : ViewModel() {
	@AssistedInject.Factory
	interface Factory {
		fun create(saveStateHandle: SavedStateHandle): HomeViewModel
	}

	//get from saveStateHandle
	private val stateFlow = MutableStateFlow<List<ArticleItem>>(emptyList())

//	init {
//		viewModelScope.launch { fetch() }
//	}

	fun getContent(): Flow<List<ArticleItem>> {
		return stateFlow
	}


	fun fetch() {
		Timber.v("fetch articles")
		viewModelScope.launch(Dispatchers.IO) {
			val items = articleService.getArticles().map(::ArticleItem)
			Timber.v("fetch articles: $items")
			stateFlow.value = items
		}
	}
}