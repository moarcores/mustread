package com.itmo.mustread.books.model

import java.time.Year

data class BookModel(
    val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val pictureLink: String
)