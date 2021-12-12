package com.itmo.mustread.books.entity

import javax.persistence.*

@Entity
class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null

    @Column(unique = true)
    var name: String? = null

    constructor()

    constructor(name:String) {
        this.name = name
    }
}