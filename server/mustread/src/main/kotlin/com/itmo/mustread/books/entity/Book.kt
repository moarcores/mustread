package com.itmo.mustread.books.entity

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField
import org.springframework.stereotype.Indexed
import java.util.Date
import javax.persistence.*


@Entity
@Indexed
class Book {
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
    private val modifyDate: Date? = null

    @FullTextField
    var title: String? = null

    @Cascade(CascadeType.ALL)
    @ManyToOne
    var author: Author? = null
    var year: Int? = null

    constructor()

    constructor(id: Int) {
        this.id = id
    }

    constructor(title: String, author: Author) {
        this.title = title
        this.author = author
    }
}