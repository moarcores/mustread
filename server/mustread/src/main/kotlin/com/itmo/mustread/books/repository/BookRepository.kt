package com.itmo.mustread.books.repository

import com.itmo.mustread.books.entity.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Int> {
    fun findFirst10BooksByTitleContainsIgnoreCase(s: String): List<Book>
}

@Repository
class BookRepoImpl(
    @Autowired
    val base: BookRepository
    )
{
    fun getAllSortedByTime():List<Book> {
        return base.findAll(sortByTimeDesc())
    }

    private fun sortByTimeDesc(): Sort {
        return Sort.by(Sort.Direction.DESC, "modifyDate")
    }
}