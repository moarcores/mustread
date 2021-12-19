package com.itmo.mustread.users.model

import com.itmo.mustread.books.model.BookModel

data class FeedDto (
    val readBook: ReadBookDto,
    val userName: String,
)