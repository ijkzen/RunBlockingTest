import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val str = NetworkHandler().post()
    println(str)
}