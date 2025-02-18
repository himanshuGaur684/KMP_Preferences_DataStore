package gaur.himanshu.youtube.kmppreferencesdatastore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform