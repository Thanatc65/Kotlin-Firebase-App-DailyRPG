package com.rpgtasks

object FakeAuth {
    data class FakeUser(
        val id: String
    )
    val currentUser = FakeUser("user1")
}