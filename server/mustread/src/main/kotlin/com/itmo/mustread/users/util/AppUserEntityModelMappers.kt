package com.itmo.mustread.users.util

import com.itmo.mustread.users.model.Status
import com.itmo.mustread.users.model.UserModel
import com.itmo.mustread.users.entity.User
import kotlin.streams.toList

fun User.toModel() = kotlin.runCatching {
    UserModel(
        id = this.id?: 0,
        username = this.username ?: "",
        password = this.password ?: "",
        status = this.status ?: Status.OFFLINE,
        subscriptions = this.subscriptions.stream().mapToInt() { user: User -> user.id!! }.toList()
    )
}.getOrElse { exception -> throw IllegalStateException("Some of user fields are null", exception) }