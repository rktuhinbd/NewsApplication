package org.rktuhinbd.news_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.rktuhinbd.news_app.data.Article
import org.rktuhinbd.news_app.utils.Resource
import org.rktuhinbd.news_app.utils.articles

class HeadlinesViewModel : ViewModel() {

    private val _newsArticles = MutableStateFlow<Resource<List<Article>>>(Resource.Init)
    val newsArticlesObserver = _newsArticles.asStateFlow()

    init {
        getHeadlines()
    }

    private fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsArticles.emit(Resource.Loading)
            delay(2000)
            try {
                val response = articles
                _newsArticles.emit(Resource.Success(response))
            } catch (e: Exception) {
                _newsArticles.emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }
}