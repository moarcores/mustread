package com.itmo.mustread.books.controller

import com.itmo.mustread.books.model.BookRequestDto
import com.itmo.mustread.books.model.BookResponseDto
import com.itmo.mustread.books.service.BookService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(val bookService: BookService) {

    @PostMapping("/add")
    @Operation(
        summary = "Add new book",
        //security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun addBook(@RequestBody request: BookRequestDto): BookResponseDto {
        return bookService.addBook(request)
    }

}