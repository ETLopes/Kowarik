package dev.eduardolopes.kowarik

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
        )
    }
    routing {
        get("/") { call.respondText("Ktor: ${Greeting().greet()}") }
        get("/health") { call.respondText("UP") }
        get("/vendors") {
            val mockVendors =
                    mapOf(
                            "data" to
                                    Vendors(
                                            vendors =
                                                    listOf(
                                                            Vendor(
                                                                    id = 1,
                                                                    name = "Vendor One",
                                                                    description =
                                                                            "Description for Vendor One",
                                                                    imageUrl =
                                                                            "http://example.com/image1.jpg",
                                                                    phone = "123-456-7890",
                                                                    address =
                                                                            "123 Vendor St, City, Country",
                                                                    latitude = 12.34,
                                                                    longitude = 56.78,
                                                                    rating = 4.5,
                                                                    reviews =
                                                                            listOf(
                                                                                    Review(
                                                                                            id = 1,
                                                                                            vendorId =
                                                                                                    1,
                                                                                            rating =
                                                                                                    5.0,
                                                                                            comment =
                                                                                                    "Great service!"
                                                                                    ),
                                                                                    Review(
                                                                                            id = 2,
                                                                                            vendorId =
                                                                                                    1,
                                                                                            rating =
                                                                                                    4.0,
                                                                                            comment =
                                                                                                    "Good quality products."
                                                                                    )
                                                                            )
                                                            ),
                                                            Vendor(
                                                                    id = 2,
                                                                    name = "Vendor Two",
                                                                    description =
                                                                            "Description for Vendor Two",
                                                                    imageUrl =
                                                                            "http://example.com/image2.jpg",
                                                                    phone = "987-654-3210",
                                                                    address =
                                                                            "456 Vendor Ave, City, Country",
                                                                    latitude = 23.45,
                                                                    longitude = 67.89,
                                                                    rating = 4.0,
                                                                    reviews =
                                                                            listOf(
                                                                                    Review(
                                                                                            id = 3,
                                                                                            vendorId =
                                                                                                    2,
                                                                                            rating =
                                                                                                    4.5,
                                                                                            comment =
                                                                                                    "Very satisfied!"
                                                                                    ),
                                                                                    Review(
                                                                                            id = 4,
                                                                                            vendorId =
                                                                                                    2,
                                                                                            rating =
                                                                                                    3.5,
                                                                                            comment =
                                                                                                    "Decent experience."
                                                                                    )
                                                                            )
                                                            )
                                                    )
                                    )
                    )
            call.respond(mockVendors)
        }
    }
}
