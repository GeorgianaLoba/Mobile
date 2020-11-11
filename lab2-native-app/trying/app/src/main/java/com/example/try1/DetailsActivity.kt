package com.example.try1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_view_activity)
        val movie = intent.getParcelableExtra("movie") as Movie?
        show_details(movie)
    }

    fun show_details(movie: Movie?){
        if (movie != null) {
            findViewById<TextView>(R.id.titleInput).setText(movie.title)
            findViewById<TextView>(R.id.directorInput).setText(movie.director)
            findViewById<TextView>(R.id.dateInput).setText(movie.date.toString())
            findViewById<TextView>(R.id.ratingInput).setText(movie.rating.toString())
            findViewById<TextView>(R.id.reviewInput).setText(movie.review)
        }
    }

    fun go_back(view: View) {
        intent = Intent()
        finish()
    }
}