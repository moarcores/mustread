package com.itmo.mustread.users.service

import com.itmo.mustread.books.entity.Book
import com.itmo.mustread.books.repository.BookRepository
import com.itmo.mustread.users.entity.ReadBook
import com.itmo.mustread.users.entity.WantBook
import com.itmo.mustread.users.model.BookWantListDto
import com.itmo.mustread.users.model.FeedDto
import com.itmo.mustread.users.model.ReadBookDto
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

        user.wantBook.add(WantBook(bookId))
        userRepository.save(user)
    }

    fun getWantList(userName: String): BookWantListDto {
        val user = userRepository.findUserByUsername(userName)

        return BookWantListDto(
            user.wantBook.map { wantBook -> wantBook.book!!.toModel() }
        )
    }

    fun getWantListById(id: Int): BookWantListDto {
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            return BookWantListDto(emptyList())
        }

        return BookWantListDto(
            user.get().wantBook.map { wantBook -> wantBook.book!!.toModel() }
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

    fun getReadBooks(userName: String): List<ReadBookDto> {
        val user = userRepository.findUserByUsername(userName)

        return user.readBooks.map { b -> ReadBookDto(b.book!!.toModel(), b.rating) }

    }

    fun getReadBooksById(id: Int): List<ReadBookDto> {
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            return emptyList()
        }

        return user.get().readBooks.map { b -> ReadBookDto(b.book!!.toModel(), b.rating) }
    }

    fun makeFeed(userName: String): List<FeedDto> {
        val res: MutableList<Pair<ReadBook, String>> = mutableListOf()

        val user = userRepository.findUserByUsername(userName)
        for (friend in user.subscriptions) {
            res.addAll(friend.readBooks.map { b -> Pair(b, friend.username!!) })
        }

        res.sortByDescending { it.first.modifyDate }

        return res.map { e ->
            FeedDto(
                ReadBookDto(e.first.book!!.toModel(), e.first.rating),
                e.second
            )
        }
    }
}