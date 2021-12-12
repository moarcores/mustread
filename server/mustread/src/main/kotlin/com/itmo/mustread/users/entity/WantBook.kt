package com.itmo.mustread.users.entity

import com.itmo.mustread.books.entity.Book
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
class WantBook: Comparable<WantBook> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    val createDate: Date? = null

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    val modifyDate: Date? = null

    @ManyToOne
    var book: Book? = null

    constructor()

    constructor(boodId: Int) {
        this.book = Book(boodId)
    }

    override fun compareTo(other: WantBook): Int {
        if (this.modifyDate == null) {
            if(other.modifyDate == null){
                return 0
            }
            return -1
        } else if (other.modifyDate == null) {
            return 1
        }

        if (this.modifyDate < other.modifyDate) {
            return -1
        }
        if (this.modifyDate == other.modifyDate) {
            return 0
        }
        return 1
    }
}