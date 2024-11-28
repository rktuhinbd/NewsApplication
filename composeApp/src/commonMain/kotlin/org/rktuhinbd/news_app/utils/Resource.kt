package org.rktuhinbd.news_app.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

sealed class Resource<out T> {
    data object Init : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()

    @Composable
    fun DisplayResult(
        onInit: (@Composable () -> Unit)? = null,
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (String) -> Unit,
        transitionSpec: ContentTransform = scaleIn(tween(durationMillis = 400))
                + fadeIn(tween(durationMillis = 800))
                togetherWith scaleOut(tween(durationMillis = 400))
                + fadeOut(
            tween(durationMillis = 800)
        )
    ) {

        AnimatedContent(
            targetState = this,
            transitionSpec = { transitionSpec },
            label = "Content Animation"
        ) { state ->
            when (state) {
                is Init -> {
                    onInit?.invoke()
                }

                is Loading -> {
                    onLoading()
                }

                is Success -> {
                    onSuccess(state.data)
                }

                is Error -> {
                    onError(state.message)
                }
            }
        }
    }
}