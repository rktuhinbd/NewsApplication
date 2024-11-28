package org.rktuhinbd.news_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform