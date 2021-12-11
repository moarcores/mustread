package com.itmo.mustread.users.controller

import com.itmo.mustread.users.entity.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subscription")
class SubscriptionController() {
    @PostMapping("/{id}")
    @Operation(
        summary = "Subscribe to user with id",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun subscribeTo(@PathVariable id: Int, @AuthenticationPrincipal user: User) {
        println(user)
    }
}