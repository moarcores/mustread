package com.itmo.mustread.users.api.model

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
