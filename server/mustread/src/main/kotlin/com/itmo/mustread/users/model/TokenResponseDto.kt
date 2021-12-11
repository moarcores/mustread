package com.itmo.mustread.users.model

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
