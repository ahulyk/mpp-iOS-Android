package org.kotlin.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

expect fun platformName(): String

internal expect val applicationDispatcher: CoroutineDispatcher

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

expect fun customHttpClient(): HttpClient

private fun createClient(): HttpClient {
    return customHttpClient().config {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                register(Hello.`$serializer`)
            }
        }
    }
}

fun makeRestCall(callback: (Hello) -> Unit) {
    val address = "https://my-json-server.typicode.com/typicode/demo/profile"
    GlobalScope.apply {
        launch(applicationDispatcher) {
            val result = createClient().get<Hello>(address)
            callback(result)
        }
    }
}

@Serializable
data class Hello(
    val name: String
)
