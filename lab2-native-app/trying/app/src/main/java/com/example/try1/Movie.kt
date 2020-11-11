package com.example.try1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(val title: String, val director: String, val date: Date, val rating: Int, val review: String)
    :Parcelable