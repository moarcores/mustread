package com.itmo.mustread.users.controller

import com.itmo.mustread.books.model.BookModel
import com.itmo.mustread.books.model.BookRequestDto
import com.itmo.mustread.books.model.BookResponseDto
import com.itmo.mustread.users.model.BookWantListDto
import com.itmo.mustread.users.service.UserBookService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
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

    @PostMapping("/wantlist")
    @Operation(
        summary = "Get want list",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun getWantList(@AuthenticationPrincipal user: User): BookWantListDto {
        return userBookService.getWantList(user.username)
    }

}