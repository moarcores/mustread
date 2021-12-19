package com.itmo.mustread.users.entity

import com.itmo.mustread.books.entity.Book
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.Constraint

@Entity
class ReadBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private val createDate: Date? = null

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    val modifyDate: Date? = null

    @ManyToOne
    var book: Book? = null
    @Column(columnDefinition = "int4 CHECK (rating >= 1 and rating <= 10)")
    var rating: Int? = null

    constructor()

    constructor(bookId: Int, rating:Int?) {
        this.book = Book(bookId)
        this.rating = rating
    }
}