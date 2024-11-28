package org.rktuhinbd.news_app.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import newsapplication.composeapp.generated.resources.Res
import newsapplication.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.rktuhinbd.news_app.data.Article
import org.rktuhinbd.news_app.utils.Resource

@Composable
fun HeadlineScreen() {

    val headlineViewModel = HeadlinesViewModel()

    val uiState = headlineViewModel.newsArticlesObserver.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(56.dp).fillMaxWidth(),
                backgroundColor = Color.DarkGray,
                title = { Text(text = "Headlines", color = Color.White) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            when (uiState.value) {
                is Resource.Error -> {

                }

                Resource.Init -> {

                }

                Resource.Loading -> {

                }

                is Resource.Success -> {
                    val dataList = (uiState.value as Resource.Success).data

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(dataList) {
                            HeadlineCard(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeadlineCard(data: Article) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        AsyncImage(
            modifier = Modifier
                .size(84.dp)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = painterResource(Res.drawable.compose_multiplatform),
            model = data.urlToImage,
            contentScale = ContentScale.Crop,
            contentDescription = "Sample Image",
            onError = {
                println("IMAGE_ERROR: ${it.result.throwable.cause}")
            }
        )

        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(
                text = data.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(600)
                )
            )

            if (!data.description.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = data.description,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(400)
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Author: ${data.author ?: "Unknown"}",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight(400)
                )
            )
        }
    }
}
