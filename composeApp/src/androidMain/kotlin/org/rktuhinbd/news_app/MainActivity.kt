package org.rktuhinbd.news_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.rktuhinbd.news_app.presentation.HeadlineScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HeadlineScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    HeadlineScreen()
}