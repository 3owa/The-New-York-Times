package com.newyorktimes.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.newyorktimes.core.navigation.fromCustom
import com.newyorktimes.features.news.domain.model.Article
import com.newyorktimes.features.news.presentation.detail.DetailScreen
import com.newyorktimes.features.news.presentation.home.HomeScreen
import kotlin.reflect.typeOf


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {

    SharedTransitionLayout {

        NavHost(
            navController = navController,
            startDestination = Routes.Home
        ) {
            composable<Routes.Home> {
                HomeScreen(
                    navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                ) }

            composable<Routes.Detail>(
                typeMap = mapOf(
                    typeOf<Article>() to NavType.fromCustom<Article>()
                )
            ) { backStackEntry ->
                val detail: Routes.Detail = backStackEntry.toRoute<Routes.Detail>()
                DetailScreen(
                    navController,
                    detail.article,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                )
            }

        }
    }

}