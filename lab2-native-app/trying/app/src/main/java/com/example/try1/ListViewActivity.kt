package com.example.try1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.list_view_activity.*
import java.text.SimpleDateFormat
import java.util.*

class ListViewActivity: AppCompatActivity() {
    private lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view_activity)
        //Realm.deleteRealm(Realm.getDefaultConfiguration())
        realm = Realm.getDefaultInstance()
        //initMovies()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(realm)
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
        realm.beginTransaction()
        val m = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        m.title = movie.title
        m.rating = movie.rating
        m.director = movie.director
        m.review = movie.review
        m.date = movie.date
        realm.commitTransaction()
    }

    private fun edit_movie(movie: Movie, position: Int){
        val movies = realm.where(MovieDTO::class.java).findAll()
        realm.beginTransaction()
        val id = movies[position]?.id
        val m = realm.where(MovieDTO::class.java).equalTo("id", id).findFirst()!!
        m.title = movie.title
        m.rating = movie.rating
        m.director = movie.director
        m.review = movie.review
        realm.commitTransaction()
    }


    private fun initMovies(){
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        realm.beginTransaction()
        val movie1 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie1.title = "Night On Earth"
        movie1.director = "Jim Jarmusch"
        movie1.rating = 9
        movie1.date = simpleDateFormat.parse("03-04-1999")
        val movie2 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie2.title = "In the Mood for Love"
        movie2.director = "Wong Kar-wai"
        movie2.rating = 10
        movie2.date = simpleDateFormat.parse("07-04-2001")
        val movie3 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie3.title = "Pan's labyrinth"
        movie3.director = "Guillermo del Toro"
        movie3.rating = 10
        movie3.date = simpleDateFormat.parse("10-04-2006")
        val movie4 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie4.title = "Vertigo"
        movie4.director = "Alfred Hitchcock"
        movie4.rating = 10
        movie4.date = simpleDateFormat.parse("10-04-1958")
        val movie5 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie5.title = "Her"
        movie5.director = "Spike Jonze"
        movie5.rating = 10
        movie5.date = simpleDateFormat.parse("02-02-2014")
        val movie6 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie6.title = "Medianeras"
        movie6.director = "Gustavo Taretto"
        movie6.rating = 7
        movie6.date = simpleDateFormat.parse("10-12-2011")
        val movie7 = realm.createObject(MovieDTO::class.java, UUID.randomUUID().toString())
        movie7.title = "Reality Bites"
        movie7.director = "Jim Jarmusch"
        movie7.rating = 9
        movie7.date = simpleDateFormat.parse("10-04-1999")
        realm.commitTransaction()
    }
}