package com.itmo.mustread.users.util

import com.itmo.mustread.books.entity.Author
import com.itmo.mustread.books.model.AuthorModel

fun Author.toModel() = AuthorModel(
    this.id ?: 0,
    this.name ?: ""
)