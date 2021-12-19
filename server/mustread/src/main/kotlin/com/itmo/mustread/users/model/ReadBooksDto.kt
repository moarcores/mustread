package com.itmo.mustread.users.model

import com.itmo.mustread.books.model.BookModel

data class ReadBooksDto (
    val bookModel: BookModel,
    val rating: Int?
)