import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NetworkHandler {

    suspend fun post(): String = suspendCancellableCoroutine { continuation ->

        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .url("https://www.bing.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                continuation.resume(body ?: "")
            }

        })
    }
}