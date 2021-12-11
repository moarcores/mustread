package com.itmo.mustread.users.entity

import com.itmo.mustread.users.model.Status
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null
    var name: String? = null
    var password: String? = null
    var status: Status? = null
    @ManyToMany
    var subscriptions: List<User>? = null;

    constructor()

    constructor(name: String?, password: String?, status: Status?) {
        this.name = name
        this.password = password
        this.status = status
    }
}