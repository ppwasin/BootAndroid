package com.boot.compose.bottombar.screen

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.boot.entrypoint.components.AppScaffold
import com.github.zsoltk.compose.router.Router

data class NestedItem(val title: String, val author: String) {
    companion object {
        val mock = (1..30).map {
            NestedItem("Title$it", "Author$it")
        }
    }
}

sealed class NestedRouting {
    object FirstScreen : NestedRouting()
    data class SecondScreen(val nestedItem: NestedItem) : NestedRouting()

    val route = javaClass.simpleName
}

@Composable
fun NestedScreen(
    navController: NavHostController,
    scrollState: LazyListState,
    nestedItems: List<NestedItem>?
) {

    NavHost(navController, startDestination = NestedRouting.FirstScreen.route) {
        composable(NestedRouting.FirstScreen.route) {
            AppScaffold(title = "Book List") {
                if (nestedItems.isNullOrEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            "Empty",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    LazyColumnFor(items = nestedItems, state = scrollState) { item ->
                        Box(modifier = Modifier.clickable(onClick = {
//                            navController.navigate(NestedRouting.SecondScreen.)
//                            backStack.push(
//                                NestedRouting.SecondScreen(
//                                    item
//                                )
//                            )
                        })) {
                            BookItem(
                                nestedItem = item
                            )
                        }

                    }
                }
            }
        }
    }
    Router<NestedRouting>(NestedRouting.FirstScreen) { backStack ->
        when (val routing = backStack.last()) {
            NestedRouting.FirstScreen ->
                AppScaffold(title = "Book List") {
                    if (nestedItems.isNullOrEmpty()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                "Empty",
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        LazyColumnFor(items = nestedItems, state = scrollState) { item ->
                            Box(modifier = Modifier.clickable(onClick = {
                                backStack.push(
                                    NestedRouting.SecondScreen(
                                        item
                                    )
                                )
                            })) {
                                BookItem(
                                    nestedItem = item
                                )
                            }

                        }
                    }
                }
            is NestedRouting.SecondScreen ->
                AppScaffold(title = routing.nestedItem.title, backNavigation = backStack::pop) {
                    BookItem(nestedItem = routing.nestedItem)
                }
        }
    }

}

@Composable
fun BookItem(
    nestedItem: NestedItem
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        BookItemContent(nestedItem)
    }
}

@Composable
fun BookItemContent(
    nestedItem: NestedItem
) {
    Row(modifier = Modifier.padding(8.dp).fillMaxWidth()) {

        Column {
            Text(
                text = nestedItem.title,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = nestedItem.author,
                style = MaterialTheme.typography.body2
            )
            Text(
//				text = ContextAmbient.current.resources.getString(
//					R.string.book_info_year_pages,
//					book.year,
//					book.pages
//				),
                text = "year page",
                style = MaterialTheme.typography.body2
            )
        }
    }
}
