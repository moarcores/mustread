package com.itmo.mustread.users.service

import com.itmo.mustread.users.entity.WantBook
import com.itmo.mustread.users.model.BookWantListDto
import com.itmo.mustread.users.repository.UserRepository
import com.itmo.mustread.users.repository.WantBookRepository
import com.itmo.mustread.users.util.toModel
import org.springframework.stereotype.Service

@Service
class UserBookService(
    private val userRepository: UserRepository,
    private val wantBookRepository: WantBookRepository
) {

    fun addBookToWantList(userName: String, bookId: Int) {
        val user = userRepository.findUserByUsername(userName)

        //check book exists
        val wantBook = wantBookRepository.findWantBookByBookId(bookId) ?: return

        //check if already in want list
        if (user.wantBook.contains(wantBook)) {
            return
        }

        user.wantBook.add(WantBook(bookId))
        userRepository.save(user)
    }

    fun getWantList(userName: String): BookWantListDto {
        val user = userRepository.findUserByUsername(userName)

        return BookWantListDto(
            user.wantBook.map { wantBook -> wantBook.book!!.toModel() }
        )
    }

    fun removeFromWantList(userName: String, bookId: Int) {
        val user = userRepository.findUserByUsername(userName)

        val wantBook = wantBookRepository.findWantBookByBookId(bookId) ?: return
        user.wantBook.remove(wantBook)
        userRepository.save(user)
    }

    fun rateBook(userName:String, bookId: Int, rating: Int) {

    }
}