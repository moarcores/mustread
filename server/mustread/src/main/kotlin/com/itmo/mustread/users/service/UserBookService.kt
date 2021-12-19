package com.itmo.mustread.users.service

import com.itmo.mustread.books.repository.BookRepository
import com.itmo.mustread.users.entity.ReadBook
import com.itmo.mustread.users.entity.WantBook
import com.itmo.mustread.users.model.BookWantListDto
import com.itmo.mustread.users.model.ReadBooksDto
import com.itmo.mustread.users.repository.UserRepository
import com.itmo.mustread.users.repository.WantBookRepository
import com.itmo.mustread.users.util.toModel
import org.springframework.stereotype.Service

@Service
class UserBookService(
    private val userRepository: UserRepository,
    private val wantBookRepository: WantBookRepository,
    private val bookRepository: BookRepository
) {

    fun addBookToWantList(userName: String, bookId: Int) {
        val user = userRepository.findUserByUsername(userName)

        //check book exists
        if (bookRepository.findById(bookId).isEmpty) {
            return
        }

        //check if already in want list

        user.wantBook.forEach { if (it.book?.id == bookId) return }
//        for (b in user.wantBook) {
//            if (b.book?.id == bookId) {
//                return
//            }
//        }

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

    fun rateBook(userName: String, bookId: Int, rating: Int?) {
        val user = userRepository.findUserByUsername(userName)

        //check book exists
        if (bookRepository.findById(bookId).isEmpty) {
            return
        }

        for (b in user.wantBook) {
            if (b.book?.id == bookId) {
                user.wantBook.remove(b)
                break
            }
        }

        user.readBooks.forEach {
            if (it.book?.id == bookId) {
                it.rating = rating
                userRepository.save(user)
                return
            }
        }

        user.readBooks.add(ReadBook(bookId, rating))
        userRepository.save(user)
    }

    fun getReadBooks(userName: String): List<ReadBooksDto> {
        val user = userRepository.findUserByUsername(userName)

        return user.readBooks.map { b -> ReadBooksDto(b.book!!.toModel(), b.rating) }

    }
}