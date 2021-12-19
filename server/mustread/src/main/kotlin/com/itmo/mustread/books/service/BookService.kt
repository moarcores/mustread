package com.itmo.mustread.books.service

import com.itmo.mustread.books.entity.Author
import com.itmo.mustread.books.entity.Book
import com.itmo.mustread.books.model.BookRequestDto
import com.itmo.mustread.books.model.BookResponseDto
import com.itmo.mustread.books.model.GetBooksDto
import com.itmo.mustread.books.model.SearchResponse
import com.itmo.mustread.books.repository.AuthorRepository
import com.itmo.mustread.books.repository.BookRepoImpl
import com.itmo.mustread.users.util.toModel
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepoImpl,
    val authorRepository: AuthorRepository
) {
    fun addBook(req: BookRequestDto): BookResponseDto {
        var author = authorRepository.findAuthorByName(req.author)
        if (author == null) {
            author = Author(req.author)
        }
        val book = bookRepository.base.save(Book(req.title, author, req.year, req.pictureLink))
        println(book.author!!.id)
        return BookResponseDto(book.id ?: 0)
    }

    fun getAll(): GetBooksDto {
        val books = bookRepository.getAllSortedByTime()
        return GetBooksDto(books.map { it.toModel() })
    }

    fun search(str: String): SearchResponse {
        val books = bookRepository.base.findFirst10BooksByTitleContainsIgnoreCase(str)
        val authors = authorRepository.findTop10AuthorsByNameContainsIgnoreCase(str)
        return SearchResponse(
            books = books.map { b -> b.toModel() },
            authors = authors.map { a -> a.toModel()}
        )
    }
}