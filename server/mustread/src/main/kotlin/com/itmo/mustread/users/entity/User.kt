package com.itmo.mustread.users.entity

import com.itmo.mustread.users.model.Status
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
    var subscriptions: List<User>? = null;

    constructor()

    constructor(username: String?, password: String?, status: Status?) {
        this.username = username
        this.password = password
        this.status = status
    }
}