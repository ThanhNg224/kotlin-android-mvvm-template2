package com.thanhng224.app.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thanhng224.app.feature.auth.presentation.ui.LoginScreen
import com.thanhng224.app.feature.onboarding.presentation.ui.OnboardingScreen
import com.thanhng224.app.feature.product.presentation.ui.HomeScreen
import com.thanhng224.app.feature.product.presentation.viewmodel.HomeViewModel
import com.thanhng224.app.presentation.navigation.Screen
import com.thanhng224.app.presentation.screen.FavoritesScreen
import com.thanhng224.app.presentation.screen.ProfileScreen
import com.thanhng224.app.presentation.screen.SettingsScreen
import com.thanhng224.app.presentation.ui.theme.Dimens
import com.thanhng224.app.presentation.ui.theme.KotlinAndroidTemplateTheme
import com.thanhng224.app.presentation.viewmodel.AppViewModel

@Composable
fun App(
    appViewModel: AppViewModel = hiltViewModel()
) {
    val items = listOf(Screen.Home, Screen.Favorites, Screen.Profile, Screen.Settings)
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val productDetailsState by homeViewModel.productDetailsState.collectAsState()
    val startDestination by appViewModel.startDestination.collectAsStateWithLifecycle()
    val isDarkMode by appViewModel.isDarkMode.collectAsStateWithLifecycle()

    if (startDestination == null) {
        // Show loading while determining start destination
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        return
    }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Favorites.route,
        Screen.Profile.route,
        Screen.Settings.route
    )

    KotlinAndroidTemplateTheme(darkTheme = isDarkMode) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination!!
                    ) {
                        composable(Screen.Onboarding.route) {
                            OnboardingScreen(
                                onGetStarted = {
                                    appViewModel.completeOnboarding()
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Screen.Login.route) {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(Screen.Home.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Screen.Home.route) { HomeScreen(productState = productDetailsState) }
                        composable(Screen.Favorites.route) { FavoritesScreen() }
                        composable(Screen.Profile.route) { ProfileScreen() }
                        composable(Screen.Settings.route) {
                            SettingsScreen(
                                onLogout = {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                },
                                isDarkTheme = isDarkMode,
                                onDarkThemeChanged = appViewModel::setDarkMode
                            )
                        }
                    }

                    if (showBottomBar) {
                        FloatingNavBar(
                            items = items,
                            navController = navController,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FloatingNavBar(
    items: List<Screen>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = Dimens.spaceMedium)
            .fillMaxWidth()
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = Dimens.mediumElevation,
            shadowElevation = Dimens.mediumElevation,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                tonalElevation = 0.dp,
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            if (screen is Screen.Favorites && screen.unreadCount > 0) {
                                BadgedBox(badge = { Badge { Text("${screen.unreadCount}") } }) {
                                    Icon(screen.icon, contentDescription = screen.title)
                                }
                            } else {
                                val scale by animateFloatAsState(
                                    if (selected) 1.15f else 1f,
                                    label = "iconScale"
                                )
                                Icon(
                                    screen.icon,
                                    contentDescription = screen.title,
                                    modifier = Modifier.graphicsLayer {
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                )
                            }
                        },
                        label = {
                            AnimatedVisibility(visible = selected) { Text(screen.title) }
                        },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    }
}
