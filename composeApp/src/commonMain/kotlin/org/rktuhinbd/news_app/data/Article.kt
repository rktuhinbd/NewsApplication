package org.rktuhinbd.news_app.data

data class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String = "",
    val source: Source = Source(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String? = ""
)