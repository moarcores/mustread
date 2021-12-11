package com.itmo.mustread.users.controller

import com.itmo.mustread.users.model.*
import com.itmo.mustread.users.model.AuthenticationRequest
import com.itmo.mustread.users.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    @Operation(summary = "Register new user")
    fun addUser(@RequestBody request: UserRequestDto): UserResponseDto {
        return userService.addUser(request.toModel())
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get user by id",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun getUserById(@PathVariable(value = "id") id: Int) = userService.getUserById(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")

    @PostMapping("/auth")
    @Operation(summary = "Authenticate")
    fun authUser(@RequestBody request: AuthenticationRequest): AuthenticationResult {
       return userService.authUser(request)
    }

    @PostMapping("/refresh")
    @Operation(
        summary = "Refresh authentication",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun refreshToken(authentication: Authentication) = userService.refreshToken(authentication)

    private fun UserRequestDto.toModel() = UserModel(this.username, this.password, Status.OFFLINE)
}