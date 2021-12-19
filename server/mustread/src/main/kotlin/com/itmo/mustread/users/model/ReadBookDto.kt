package com.itmo.mustread.users.model

import com.itmo.mustread.books.model.BookModel

data class ReadBookDto (
    val bookModel: BookModel,
    val rating: Int?
)