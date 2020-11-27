package com.example.try1

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class MovieDTO() : RealmObject(){
    @PrimaryKey
    var id: String? = null
    var title: String? = null
    var director: String? = null
    var date: Date? = null
    var rating: Int? = null
    var review: String? = null
}