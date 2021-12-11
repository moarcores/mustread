package com.itmo.mustread.users.model

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

data class UserModel(
    val username: String,
    val password: String,
    val status: Status
) {
    fun userDetails(): UserDetails = User(username, password, emptyList())
}