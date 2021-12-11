package com.itmo.mustread.users.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private val username: String? = null
    private val password: String? = null
    private val authority: List<GrantedAuthority>? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities
    override fun getPassword() =password.toString()
    override fun getUsername() = username.toString()
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}