package com.rpgtasks.tasks

import com.google.firebase.database.Exclude

class TodoProfile(
    var profilepicture : String = "",
    @get:Exclude var id: String = ""
)