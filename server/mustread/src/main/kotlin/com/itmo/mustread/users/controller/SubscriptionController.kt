package com.itmo.mustread.users.controller

import com.itmo.mustread.users.service.SubscriptionService
import org.springframework.security.core.userdetails.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/subscription")
class SubscriptionController(val subscriptionService: SubscriptionService) {
    @PostMapping("/{id}")
    @Operation(
        summary = "Subscribe to user with id",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun subscribeTo(@PathVariable id: Int, @AuthenticationPrincipal user: User) {
        subscriptionService.subscriberUserTo(user.username, id)
    }

    @PostMapping("remove/{id}/")
    @Operation(
        summary = "Subscribe to user with id",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun unsubscribeFrom(@PathVariable id: Int, @AuthenticationPrincipal user: User) {
        subscriptionService.unsubscribeUserFrom(user.username, id)
    }
}