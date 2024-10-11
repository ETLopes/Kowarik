package dev.eduardolopes.kowarik.networking

import dev.eduardolopes.kowarik.util.NetworkError
import dev.eduardolopes.kowarik.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.http.parseQueryString
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class KowarikClient(
    private val httpClient: HttpClient
) {
    suspend fun getKowarikVendors(): Result<Vendors, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "http://192.168.68.100:8080/vendors"
            )
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val vendors = response.body<Response>()
                Result.Success(vendors.data)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }
}