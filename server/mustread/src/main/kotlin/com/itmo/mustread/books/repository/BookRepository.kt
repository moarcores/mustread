package com.itmo.mustread.books.repository

import com.itmo.mustread.books.entity.Book
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Int> {
}

@Repository
class BookRepoImpl(val bookRepository: BookRepository) {
    fun getAllSortedByTime():List<Book> {
        return bookRepository.findAll(sortByTimeDesc())
    }

    private fun sortByTimeDesc(): Sort {
        return Sort.by(Sort.Direction.DESC, "modify_date")
    }
}