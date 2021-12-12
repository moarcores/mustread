package com.itmo.mustread.users.repository

import com.itmo.mustread.users.entity.WantBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WantBookRepository: JpaRepository<WantBook, Int> {
    fun findWantBookByBookId(id: Int): WantBook?
}