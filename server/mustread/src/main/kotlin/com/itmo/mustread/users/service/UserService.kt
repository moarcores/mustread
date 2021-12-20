package com.itmo.mustread.users.service

import com.itmo.mustread.common.exception.AccessDeniedException
import com.itmo.mustread.users.model.*
import com.itmo.mustread.users.model.AuthenticationRequest
import com.itmo.mustread.users.entity.User
import com.itmo.mustread.users.repository.UserRepository
import com.itmo.mustread.users.util.toModel
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val tokenManager: JwtTokenManager,
        private val passwordEncoder: PasswordEncoder
) {

    private fun findUser(name: String) = getUserByUsername(name).toModel()

    fun addUser(userModel: UserModel): UserResponseDto {
        val user = userRepository.save(userModel.toEntity())

        return UserResponseDto(user.id ?: 0, user.username ?: "")
    }

    fun getUserById(id: Int): UserModel? {
        val user = userRepository.findById(id)
        if (user.isEmpty) return null
        return user.get().toModel()
    }

    fun getUserByUsername(username: String) = userRepository.findUserByUsername(username)

    fun authUser(request: AuthenticationRequest): AuthenticationResult {
        val user = findUser(request.username)

        if (request.password != user.password)
            throw AccessDeniedException("Invalid password")

        val accessToken = tokenManager.generateToken(user.userDetails())
        val refreshToken = tokenManager.generateRefreshToken(user.userDetails())

        return AuthenticationResult(accessToken, refreshToken)
    }

    fun refreshToken(authentication: Authentication): AuthenticationResult {
        val refreshToken = authentication.credentials as String
        val principal = authentication.principal as UserDetails
        val accessToken = tokenManager.generateToken(principal)

        return AuthenticationResult(accessToken, refreshToken)
    }

    fun getAllUsers(): List<UserModel> {
        return userRepository.findAll().stream().map { user: User -> user.toModel() }.toList()
    }

    private fun UserModel.toEntity() = User(this.username, this.password, this.status)
}