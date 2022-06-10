package com.rpgtasks.tasks

import com.google.firebase.firestore.Exclude

data class Todotask(
    var name: String = "",
    @get:Exclude var id: String = "",
)
