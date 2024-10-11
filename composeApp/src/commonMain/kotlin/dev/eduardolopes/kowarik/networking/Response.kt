package dev.eduardolopes.kowarik.networking

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val data: Vendors
)

@Serializable
data class Vendors(
    val vendors: List<Vendor>
)

@Serializable
data class Vendor(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val phone: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double,
    val reviews: List<Review>
)

@Serializable
data class Review(
    val id: Int,
    val vendorId: Int,
    val rating: Double,
    val comment: String
)
