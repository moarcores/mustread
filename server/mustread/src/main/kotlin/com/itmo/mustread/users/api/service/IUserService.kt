package com.itmo.mustread.users.api.service

import com.itmo.mustread.users.api.model.AuthenticationRequest
import com.itmo.mustread.users.api.model.AuthenticationResult
import com.itmo.mustread.users.api.model.UserModel
import com.itmo.mustread.users.api.model.UserResponseDto
import org.springframework.security.core.Authentication

interface IUserService {
    fun addUser(userModel: UserModel): UserResponseDto
    fun getUserById(id: Int): UserResponseDto?
    fun authUser(request: AuthenticationRequest): AuthenticationResult
    fun refreshToken(authentication: Authentication): AuthenticationResult
}