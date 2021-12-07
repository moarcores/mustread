package com.itmo.mustread.users.impl.repository

import com.itmo.mustread.users.impl.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findUserByName(name: String): User
}