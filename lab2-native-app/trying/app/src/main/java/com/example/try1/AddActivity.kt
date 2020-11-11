package com.example.try1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class AddActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_view_activity)
    }

    fun add_movie(view: View) {
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

        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = simpleDateFormat.parse(dateText)
        val movie = Movie(titleText, directorText, date , ratingText.toInt(), reviewText)

        if (correct) {
            val intent = Intent()
            val bundle = Bundle()
            bundle.putParcelable("movie", movie)
            intent.putExtra("movieBundle", bundle)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun go_back(view: View) {
        intent = Intent()
        finish()
    }
}