package net.kisha.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.kisha.ui.SplashScreen
import net.kisha.ui.Users.AddUsers
import net.kisha.ui.about.AboutScreen
import net.kisha.ui.auth.LoginScreen
import net.kisha.ui.auth.SignUpScreen
import net.kisha.ui.dashboard.DashboardScreen
//import net.kisha.ui.auth.SignupScreen
import net.kisha.ui.home.HomeScreen
import net.kisha.ui.products.AddProductScreen
import net.kisha.ui.products.ProductDetailScreen
import net.kisha.ui.products.ProductListScreen
import net.kisha.ui.Users.Search
import net.kisha.ui.Users.Students
//import net.kisha.ui.dashboard.UserDashboardScreen
//import net.kisha.ui.products.UserListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH


) {


    BackHandler {
        navController.popBackStack()

        }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {


        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }


        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }


        composable(ROUTE_ADD_USER) {
            AddUsers(navController)
        }

        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUTE_VIEW_USER) {
           Students(navController = navController, viewModel = viewModel() )
        }

        composable(ROUTE_SEARCH) {
            Search(navController)
        }

        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUTE_REGISTER) {
           SignUpScreen(navController = navController) {

           }
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController){}
        }

        composable(ROUTE_ADD_PRODUCT) {
            AddProductScreen(navController = navController){}
        }

        composable(ROUTE_VIEW_PROD) {
            ProductListScreen(navController = navController, products = listOf() )
        }

//        composable(ROUTE_PROFILE) {
//            UserDashboardScreen(navController = )Screen(navController = navController )
//        }


        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController, productId)
        }










































    }
}