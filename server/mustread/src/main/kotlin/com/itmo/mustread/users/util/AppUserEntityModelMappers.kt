package com.itmo.mustread.users.util

import com.itmo.mustread.users.model.Status
import com.itmo.mustread.users.model.UserModel
import com.itmo.mustread.users.entity.User

fun User.toModel() = kotlin.runCatching {
    UserModel(
        username = this.username ?: "",
        password = this.password ?: "",
        status = this.status ?: Status.OFFLINE
    )
}.getOrElse { exception -> throw IllegalStateException("Some of user fields are null", exception) }