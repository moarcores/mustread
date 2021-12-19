package com.itmo.mustread.books.model

import java.time.Year

data class BookRequestDto(
    val title: String,
    val author: String,
    val year: Int? = null,
    val pictureLink: String? = null
)