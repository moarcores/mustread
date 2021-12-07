package com.itmo.mustread.users.api.messaging

import com.itmo.mustread.users.api.model.UserModel

data class UserCreatedEvent(val user: UserModel)

data class UserDeletedEvent(val username: String)
