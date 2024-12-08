package com.newyorktimes.features.news.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import com.newyorktimes.features.news.domain.model.Article


@Composable
fun ArticleItem(imageModifier: Modifier, titleModifier: Modifier, article: Article, onClick:()->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Image(
                painter = rememberImagePainter(article.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = titleModifier
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Published: ${article.publishedDate}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.summary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}