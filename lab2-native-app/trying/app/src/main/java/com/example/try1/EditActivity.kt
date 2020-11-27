package com.example.try1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class EditActivity: AppCompatActivity() {
    private var position: Int = 0
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_view_activity)
        this.movie = intent.getParcelableExtra("movie") as Movie?
        val aux = intent.getIntExtra("position", 0)
        if (aux != 0) this.position=aux
        init_inputs(movie)
    }

    fun init_inputs(movie: Movie?){
        if (movie != null) {
            findViewById<EditText>(R.id.titleInput).setText(movie.title)
            findViewById<EditText>(R.id.directorInput).setText(movie.director)
            findViewById<EditText>(R.id.dateInput).setText(movie.date.toString())
            findViewById<EditText>(R.id.ratingInput).setText(movie.rating.toString())
            findViewById<EditText>(R.id.reviewInput).setText(movie.review)
        }
    }

    fun edit_movie(view: View) {
        var correct = true
        val titleText = findViewById<EditText>(R.id.titleInput).text.toString()
        val directorText = findViewById<EditText>(R.id.directorInput).text.toString()
        val dateText = findViewById<EditText>(R.id.dateInput).text.toString()
        val ratingText = findViewById<EditText>(R.id.ratingInput).text.toString()
        val reviewText = findViewById<EditText>(R.id.reviewInput).text.toString()

        if (titleText == "") {
            Toast.makeText(this,"You must add title!", Toast.LENGTH_LONG).show()
            correct = false
        }
        if (directorText == "") {
            Toast.makeText(this,"You must add director!", Toast.LENGTH_LONG).show()
            correct = false
        }
        if (dateText == ""){
            Toast.makeText(this,"You must add date!", Toast.LENGTH_LONG).show()
            correct = false
        }
        if (ratingText == "") {
            Toast.makeText(this,"You must add rating!", Toast.LENGTH_LONG).show()
            correct = false
        }
        if (reviewText == "") {
            Toast.makeText(this,"You must add review!", Toast.LENGTH_LONG).show()
            correct = false
        }
        if (ratingText.toInt() > 10){
            Toast.makeText(this,"Rating must be below 10!", Toast.LENGTH_LONG).show()
            correct = false
        }

        val editedMovie = Movie(this.movie?.id!!, titleText, directorText, Date(2020,11,1), 7, reviewText)

        if(correct){
            val intent = Intent()
            intent.putExtra("movie", editedMovie)
            intent.putExtra("position", this.position)
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    fun go_back(view: View) {
        intent = Intent()
        finish()
    }
}