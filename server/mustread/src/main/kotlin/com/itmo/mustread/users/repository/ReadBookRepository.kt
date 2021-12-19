package com.itmo.mustread.users.repository

import com.itmo.mustread.users.entity.ReadBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReadBookRepository : JpaRepository<ReadBook, Int> {
    fun findReadBooksByBookTitleContains(title: String): List<ReadBook>
    fun findReadBooksByBookAuthorNameContains(name: String): List<ReadBook>
}