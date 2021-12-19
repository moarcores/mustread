package com.itmo.mustread.users.util

import com.itmo.mustread.books.entity.Book
import com.itmo.mustread.books.model.BookModel

fun Book.toModel() = BookModel(
    this.id ?: 0,
    this.title ?: "",
    this.author?.name ?: "",
    this.year ?: 0,
    this.pictureLink ?: ""
)