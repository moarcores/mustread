package com.itmo.mustread.books.service

import com.itmo.mustread.books.entity.Author
import com.itmo.mustread.books.entity.Book
import com.itmo.mustread.books.model.BookRequestDto
import com.itmo.mustread.books.model.BookResponseDto
import com.itmo.mustread.books.repository.AuthorRepository
import com.itmo.mustread.books.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository
) {
    fun addBook(req: BookRequestDto): BookResponseDto {
        var author = authorRepository.findAuthorByName(req.author)
        if (author == null){
            author = Author(req.author)
        }
        val book = bookRepository.save(Book(req.title, author))
        println(book.author!!.id)
        return BookResponseDto(book.id ?: 0)
    }
}