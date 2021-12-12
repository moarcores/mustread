package com.itmo.mustread.users.entity

import com.itmo.mustread.books.entity.Book
import com.itmo.mustread.books.entity.BookRating
import com.itmo.mustread.common.interfaces.TimeComparator
import com.itmo.mustread.users.model.Status
import com.itmo.mustread.users.model.UserModel
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.util.*
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null
    @Column(unique = true)
    var username: String? = null
    var password: String? = null
    var status: Status? = null
    @ManyToMany
    var subscriptions: MutableList<User> = mutableListOf()
    @OneToMany
    var ratedBooks: MutableList<BookRating> = mutableListOf()
    @ManyToMany
    @Cascade(CascadeType.ALL)
    @OrderBy("modify_date DESC")
    var wantBook: SortedSet<WantBook> = sortedSetOf()

    constructor()

    constructor(username: String?, password: String?, status: Status?) {
        this.username = username
        this.password = password
        this.status = status
    }
}