package com.itmo.mustread.books.repository

import com.itmo.mustread.books.entity.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Int> {
    fun findAuthorByName(name: String): Author?
    fun findTop10AuthorsByNameContainsIgnoreCase(s: String): List<Author>
}