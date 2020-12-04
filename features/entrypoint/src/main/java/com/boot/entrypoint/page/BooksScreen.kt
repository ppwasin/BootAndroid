package com.boot.entrypoint.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.boot.entrypoint.R
import com.boot.entrypoint.components.AppScaffold
import com.github.zsoltk.compose.router.Router

data class Book(val title: String, val author: String) {
	companion object {
		val mock = (1..30).map {
			Book("Title$it", "Author$it")
		}
	}
}

sealed class BooksScreenRouting {
	object FirstPage : BooksScreenRouting()
	data class DetailsPage(val book: Book) : BooksScreenRouting()
}

@Composable
fun BooksScreen(
	scrollState: LazyListState,
	books: List<Book>?
) {
	Router<BooksScreenRouting>(BooksScreenRouting.FirstPage) { backStack ->
		when (val routing = backStack.last()) {
			BooksScreenRouting.FirstPage ->
				AppScaffold(title = "Book List") {
					if (books.isNullOrEmpty()) {
						Box(modifier = Modifier.fillMaxSize()) {
							Text(
								"Empty",
								style = MaterialTheme.typography.h6,
								modifier = Modifier.align(Alignment.Center)
							)
						}
					} else {
						LazyColumnFor(items = books, state = scrollState) { item ->
							Box(modifier = Modifier.clickable(onClick = {
								backStack.push(
									BooksScreenRouting.DetailsPage(
										item
									)
								)
							})) {
								BookItem(
									book = item
								)
							}

						}
					}
				}
			is BooksScreenRouting.DetailsPage ->
				AppScaffold(title = routing.book.title, backNavigation = backStack::pop) {
					BookItem(book = routing.book)
				}
		}
	}

}

@Composable
fun BookItem(
	book: Book
) {
	Card(
		elevation = 4.dp,
		shape = RoundedCornerShape(4.dp),
		modifier = Modifier.padding(8.dp)
	) {
		BookItemContent(book)
	}
}

@Composable
fun BookItemContent(
	book: Book
) {
	Row(modifier = Modifier.padding(8.dp).fillMaxWidth()) {

		Column {
			Text(
				text = book.title,
				style = MaterialTheme.typography.h6
			)
			Text(
				text = book.author,
				style = MaterialTheme.typography.body2
			)
			Text(
//				text = ContextAmbient.current.resources.getString(
//					R.string.book_info_year_pages,
//					book.year,
//					book.pages
//				),
				text = stringResource(id = R.string.book_info_year_pages),
				style = MaterialTheme.typography.body2
			)
		}
	}
}
