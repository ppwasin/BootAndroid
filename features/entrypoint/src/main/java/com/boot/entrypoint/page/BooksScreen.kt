package com.boot.entrypoint.page

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boot.entrypoint.components.AppScaffold
import com.boot.entrypoint.sample.material_motion.VerticalTransform
import com.github.zsoltk.compose.router.Router

data class Book(val title: String, val author: String) {
	companion object {
		val mock = (1..1).map {
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
	books: List<Book>?
) {
	Router<BooksScreenRouting>(BooksScreenRouting.FirstPage) { backStack ->
		when (val routing = backStack.last()) {
			BooksScreenRouting.FirstPage ->
				AppScaffold(title = "Book List") {
					if (books.isNullOrEmpty()) {
						Stack(modifier = Modifier.fillMaxSize()) {
							Text(
								"Empty",
								style = MaterialTheme.typography.h6,
								modifier = Modifier.gravity(Alignment.Center)
							)
						}
					} else {
						LazyColumnFor(items = books) { item ->
							VerticalTransform(onStateChangeFinished = {
								backStack.push(
									BooksScreenRouting.DetailsPage(item)
								)
							}) {
								BookItem(item)
							}
						}
					}
				}
			is BooksScreenRouting.DetailsPage ->
//				AppScaffold(title = routing.book.title, backNavigation = backStack::pop) {
//					AnimatedVisibility(
//						initiallyVisible = false,
//						visible = true,
//						enter = slideInHorizontally(
//							initialOffsetX = { it / 2 },
//							animSpec = spring()
//						),
//						exit = slideOutHorizontally()
//					) {
//						val context = ContextAmbient.current
//						Column {
//							ScrollableColumn {
//								BookItem(routing.book)
//								Text(context.getString(R.string.dummy_long_string))
//							}
//						}
//
//					}
//				}
				AppScaffold(title = routing.book.title, backNavigation = backStack::pop) {
					BookItem(routing.book)
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
				text = "book_info_year_pages",
				style = MaterialTheme.typography.body2
			)
		}
	}
}
