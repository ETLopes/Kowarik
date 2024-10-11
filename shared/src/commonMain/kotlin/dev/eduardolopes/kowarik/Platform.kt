package dev.eduardolopes.kowarik

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform