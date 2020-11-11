package com.example.try1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.list_view_activity.*
import java.text.SimpleDateFormat
import java.util.*

class ListViewActivity: AppCompatActivity() {
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view_activity)
        initMovies()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(movies)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                val bundle = data?.getBundleExtra("movieBundle")
                val movie = bundle?.getParcelable<Movie>("movie")
                if (movie != null) {
                    add_movie(movie)
                    Toast.makeText(this, "added!", Toast.LENGTH_SHORT).show()
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
        if (requestCode == 5) {
            if (resultCode == Activity.RESULT_OK) {
                val movie = data?.getParcelableExtra<Movie>("movie")
                val pos = data?.getIntExtra("position", 0) as Int
                if (movie != null) {
                    edit_movie(movie, pos)
                    Toast.makeText(this, "updated!", Toast.LENGTH_SHORT).show()
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    fun go_add_movie(view: View) {
        intent = Intent(applicationContext, AddActivity::class.java)
        startActivityForResult(intent, 3)
    }

    private fun add_movie(movie: Movie) {
        movies.add(movie)
    }

    private fun edit_movie(movie: Movie, position: Int){
        movies[position] = movie;
    }


    private fun initMovies(){
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")

        val movie1 = Movie("Night on Earth", "Jim Jarmusch", simpleDateFormat.parse("03-04-1999"), 9, "Some review...")
        val movie2 = Movie("In the Mood for Love", "Wong Kar-wai", simpleDateFormat.parse("07-04-2001"), 10, "Some review...")
        val movie3 = Movie("Pan's labyrinth", "Guillermo del Toro",simpleDateFormat.parse("10-04-2006"), 10, "Some review...")
        val movie4 = Movie("Vertigo", "Alfred Hitchcock",simpleDateFormat.parse("10-04-1958"), 10, "Some review...")
        val movie5 = Movie("Her", "Spike Jonze",simpleDateFormat.parse("02-02-2014"), 10, "Some review...")
        val movie6 = Movie("Medianeras", "Gustavo Taretto",simpleDateFormat.parse("10-12-2011"), 7, "Some review...")
        val movie7 = Movie("Reality Bites", "Jim Jarmusch",simpleDateFormat.parse("10-04-1999"), 9, "Some review...")

        movies.add(movie1)
        movies.add(movie2)
        movies.add(movie3)
        movies.add(movie4)
        movies.add(movie5)
        movies.add(movie6)
        movies.add(movie7)

    }
}