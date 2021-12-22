package com.itmo.mustread.books.controller

import com.itmo.mustread.books.model.BookRequestDto
import com.itmo.mustread.books.model.BookResponseDto
import com.itmo.mustread.books.model.GetBooksDto
import com.itmo.mustread.books.model.SearchResponse
import com.itmo.mustread.books.service.BookService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/books")
class BookController(val bookService: BookService) {

    @PostMapping("/add")
    @Operation(
        summary = "Add new book",
    )
    fun addBook(@RequestBody request: BookRequestDto): BookResponseDto {
        return bookService.addBook(request)
    }

    @GetMapping("/getAll")
    @Operation(
        summary = "Get all books",
    )
    fun getBooks(): GetBooksDto {
        return bookService.getAll()
    }

    @GetMapping ("/search/{str}")
    @Operation(
        summary = "Search books or authors"
    )
    fun search(@PathVariable str: String): SearchResponse {
        return bookService.search(str)
    }

}