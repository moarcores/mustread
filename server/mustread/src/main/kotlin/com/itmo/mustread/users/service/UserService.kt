package com.itmo.mustread.users.service

import com.itmo.mustread.common.exception.AccessDeniedException
import com.itmo.mustread.users.model.*
import com.itmo.mustread.users.model.AuthenticationRequest
import com.itmo.mustread.users.entity.User
import com.itmo.mustread.users.repository.UserRepository
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

    private fun findUser(name: String) = userRepository.findUserByUsername(name).toModel()

    fun addUser(userModel: UserModel): UserResponseDto {
        val user = userRepository.save(userModel.toEntity())

        return UserResponseDto(user.id ?: 0, user.username ?: "")
    }

    fun getUserById(id: Int): UserResponseDto? {
        val user = userRepository.findById(id)

        return if (!user.isEmpty) {
            UserResponseDto(user.get().id ?: 0, user.get().username ?: "")
        } else {
            null
        }
    }

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

    private fun UserModel.toEntity() = User(this.name, this.password, this.status)

    private fun User.toModel() =
        UserModel(
            this.username ?: "",
            this.password ?: "",
            this.status ?: Status.OFFLINE
        )
}