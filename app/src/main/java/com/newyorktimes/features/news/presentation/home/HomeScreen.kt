package com.newyorktimes.features.news.presentation.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newyorktimes.R
import com.newyorktimes.core.utils.rememberForeverLazyListState
import com.newyorktimes.features.news.presentation.home.components.ArticleItem
import com.newyorktimes.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {

    val state by viewModel.homeState.collectAsState()

    with(sharedTransitionScope) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.app_name),
                            fontFamily = FontFamily(Font(R.font.font)),
                        )
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    state.isLoading -> {

                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(50.dp)
                        )
                    }

                    state.error != null -> {

                        Text(
                            text = state.error ?: "An unknown error occurred",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    state.articles.isNotEmpty() -> {

                        val lazyListState = rememberForeverLazyListState(
                            key = "news_list"
                        )

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            state = lazyListState
                        ) {
                            items(state.articles) { article ->
                                ArticleItem(
                                    imageModifier = Modifier
                                        .sharedElement(
                                            state = sharedTransitionScope.rememberSharedContentState(
                                                key = "image-${article.id}"
                                            ),
                                            animatedVisibilityScope = animatedContentScope
                                        ),
                                    titleModifier = Modifier
                                        .sharedElement(
                                            state =sharedTransitionScope.rememberSharedContentState(key = "title-${article.id}"),
                                            animatedVisibilityScope = animatedContentScope
                                        ),
                                    article = article
                                ) {
                                    navController.navigate(Routes.Detail(article))
                                }
                            }
                        }
                    }

                    else -> {
                        // Show a default empty state
                        Text(
                            text = "No articles available.",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

