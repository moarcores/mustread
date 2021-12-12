package com.itmo.mustread.users.model

import com.itmo.mustread.books.model.BookModel

data class BookWantListDto(
    val bookModels:List<BookModel>
)