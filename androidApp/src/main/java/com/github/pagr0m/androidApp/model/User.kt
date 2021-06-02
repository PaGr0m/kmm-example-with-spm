package com.github.pagr0m.androidApp.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

@Serializable
data class Address(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    var geo: Geo
)

@Serializable
data class Geo(
    var lat: String,
    var lng: String
)

@Serializable
data class Company(
    var name: String,
    var catchPhrase: String,
    var bs: String
)
