package com.itmo.mustread.books.model

data class SearchResponse(
    val books: List<BookModel>,
    val authors: List<AuthorModel>
)