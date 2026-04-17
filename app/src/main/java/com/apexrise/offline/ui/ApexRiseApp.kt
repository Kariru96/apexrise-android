package com.apexrise.offline.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apexrise.offline.R
import com.apexrise.offline.ui.screens.AddCowScreen
import com.apexrise.offline.ui.screens.CowDetailScreen
import com.apexrise.offline.ui.screens.CowsScreen
import com.apexrise.offline.ui.screens.DashboardScreen
import com.apexrise.offline.ui.screens.MilkingScreen
import com.apexrise.offline.ui.screens.ProfitsScreen
import com.apexrise.offline.ui.screens.RecordsScreen

private sealed class TopRoute(
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit,
) {
    data object Dashboard : TopRoute("dashboard", "Dashboard", { Icon(Icons.Rounded.Dashboard, null) })
    data object Cows : TopRoute("cows", "Cows", { Icon(Icons.Rounded.Pets, null) })
    data object Milking : TopRoute("milking", "Milking", { Icon(Icons.Rounded.WaterDrop, null) })
    data object Records : TopRoute("records", "Records", { Icon(Icons.AutoMirrored.Rounded.ReceiptLong, null) })
    data object Profits : TopRoute("profits", "Profits", { Icon(Icons.Rounded.AccountBalanceWallet, null) })
}

private val bottomRoutes = listOf(
    TopRoute.Dashboard,
    TopRoute.Cows,
    TopRoute.Milking,
    TopRoute.Records,
    TopRoute.Profits,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApexRiseApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("ApexRise")
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_apexrise),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            )
        },
        bottomBar = {
            BottomNav(
                navController = navController,
                currentRoute = currentRoute,
            )
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = TopRoute.Dashboard.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(TopRoute.Dashboard.route) { DashboardScreen(navController) }
            composable(TopRoute.Cows.route) { CowsScreen(navController) }
            composable("cows/new") { AddCowScreen(navController) }
            composable("cows/{cowId}") { CowDetailScreen(navController) }
            composable(
                route = "milking?cowId={cowId}",
                arguments = listOf(
                    navArgument("cowId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                ),
            ) { MilkingScreen(navController) }
            composable(TopRoute.Records.route) { RecordsScreen(navController) }
            composable(TopRoute.Profits.route) { ProfitsScreen(navController) }
        }
    }
}

@Composable
private fun BottomNav(
    navController: NavHostController,
    currentRoute: String?,
) {
    NavigationBar {
        for (dest in bottomRoutes) {
            val selected = currentRoute == dest.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigateToTopRoute(dest.route) },
                icon = dest.icon,
                label = { Text(dest.label) },
            )
        }
    }
}

private fun NavHostController.navigateToTopRoute(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
