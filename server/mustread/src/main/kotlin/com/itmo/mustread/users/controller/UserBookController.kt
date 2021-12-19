package com.itmo.mustread.users.controller

import com.itmo.mustread.users.model.BookWantListDto
import com.itmo.mustread.users.model.FeedDto
import com.itmo.mustread.users.model.RateBookRequest
import com.itmo.mustread.users.model.ReadBookDto
import com.itmo.mustread.users.service.UserBookService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/userbook")
class UserBookController(val userBookService: UserBookService) {

//    @PostMapping("/rate")
//    @Operation(
//        summary = "Rate book",
//        //security = [SecurityRequirement(name = "bearerAuth")]
//    )
//    fun rateBook(@RequestBody request: BookRequestDto): BookResponseDto {
//        return bookService.addBook(request)
//    }

    @PostMapping("/add/{id}")
    @Operation(
        summary = "Add book to want list",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun addBook(@PathVariable id: Int, @AuthenticationPrincipal user: User) {
        return userBookService.addBookToWantList(user.username, id)
    }

    @PostMapping("/remove/{id}")
    @Operation(
        summary = "Remove book from want list",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun removeBook(@PathVariable id: Int, @AuthenticationPrincipal user: User) {
        return userBookService.removeFromWantList(user.username, id)
    }

    @GetMapping("/wantlist")
    @Operation(
        summary = "Get want list",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun getWantList(@AuthenticationPrincipal user: User): BookWantListDto {
        return userBookService.getWantList(user.username)
    }

    @PostMapping("/readBook")
    @Operation(
        summary = "Rate book or add to read list",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun rateBook(@RequestBody request: RateBookRequest, @AuthenticationPrincipal user: User) {
        return userBookService.rateBook(user.username, request.id, request.rating)
    }

    @GetMapping("/getReadBooks")
    @Operation(
        summary = "Get read books",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun getReadBooks( @AuthenticationPrincipal user: User): List<ReadBookDto> {
        return userBookService.getReadBooks(user.username)
    }

    @GetMapping("/feed")
    @Operation(
        summary = "Make feed for user",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun makeFeed(@AuthenticationPrincipal user: User): List<FeedDto> {
        return userBookService.makeFeed(user.username)
    }

}