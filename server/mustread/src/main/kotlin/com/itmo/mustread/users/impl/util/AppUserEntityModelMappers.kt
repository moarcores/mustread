package com.itmo.mustread.users.impl.util

import com.itmo.mustread.users.api.model.Status
import com.itmo.mustread.users.api.model.UserModel
import com.itmo.mustread.users.impl.entity.User

fun User.toModel() = kotlin.runCatching {
    UserModel(
        name = this.name ?: "",
        password = this.password ?: "",
        status = this.status ?: Status.OFFLINE
    )
}.getOrElse { exception -> throw IllegalStateException("Some of user fields are null", exception) }