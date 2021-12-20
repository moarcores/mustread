package com.itmo.mustread.users.model

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

data class UserModel(
    val id: Int?,
    val username: String,
    val password: String,
    val status: Status,
    val subscriptions: List<Int> = listOf()
) {
    fun userDetails(): UserDetails = User(username, password, emptyList())
}