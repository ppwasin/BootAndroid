package com.boot.compose.bottombar

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.boot.compose.bottombar.screen.CountScreen
import com.boot.compose.bottombar.ui.typography
import timber.log.Timber

@Composable
fun MainScreen() {
	val navController = rememberNavController()
	
	val bottomNavigationItems = listOf(
		BottomNavigationScreens.Counting,
		BottomNavigationScreens.Scrolling,
		BottomNavigationScreens.Ghost,
		BottomNavigationScreens.ScaryBag
	)
	Scaffold(
		bottomBar = { AppBottomNavigation(navController, bottomNavigationItems) },
	) {
		MainScreenNavigationConfigurations(navController)
	}
}

@Composable
private fun AppBottomNavigation(
	navController: NavHostController,
	items: List<BottomNavigationScreens>,
) {
	BottomNavigation {
		val currentRoute = currentRoute(navController)
		Timber.v("currentRoute: $currentRoute")
		items.forEach { screen ->
			BottomNavigationItem(
				icon = { Icon(screen.icon) },
				label = { Text(text = stringResource(id = screen.resourceId), style = typography.overline) },
				selected = currentRoute == screen.route,
				onClick = {
					// This is the equivalent to popUpTo the start destination
					// In order to ensure that each time a BottomNavigationItem is selected the
					// back stack is not continuing to add destinations,
					// we pop the back stack up to the startDestination. This is consistent with
					// the behavior of using NavOptions singleTop=true, popUpTo=startDestination in the navigation runtime library.
					navController.popBackStack(navController.graph.startDestination, false)
					// This if check gives us a "singleTop" behavior where we do not create a
					// second instance of the composable if we are already on that destination
					if (currentRoute != screen.route) {
						navController.navigate(screen.route)
					}
				}
			)
		}
	}
}

@Composable
private fun MainScreenNavigationConfigurations(
	navController: NavHostController,
) {
	var count by remember { mutableStateOf(1) }
	NavHost(navController, startDestination = BottomNavigationScreens.Counting.route) {
		composable(BottomNavigationScreens.Counting.route) {
			CountScreen(name = "Test", count = count, setCount = { count = it })
		}
		composable(BottomNavigationScreens.Scrolling.route) {
			Text("Pumpkin")
			
		}
		composable(BottomNavigationScreens.Ghost.route) {
			Text("Ghost")
		}
		composable(BottomNavigationScreens.ScaryBag.route) {
			Text("ScaryBag")
		}
	}
}