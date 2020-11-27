package com.example.try1

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm


class RecyclerAdapter(var realm: Realm):
RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    //private var context: Context? = null
    private final var intent: Intent? = null
    private var context: Context? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnLongClickListener {

        val itemTitle: TextView = itemView.findViewById(R.id.Title)
        val itemDirector: TextView = itemView.findViewById(R.id.Director)
        val itemDate: TextView = itemView.findViewById(R.id.Date)
        val itemRating: TextView = itemView.findViewById(R.id.Rating)
        val itemTrash: ImageView = itemView.findViewById(R.id.trash)
        val itemEdit: ImageView = itemView.findViewById(R.id.edit)

        init {
            context = itemView.context
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(view: View?): Boolean {
            // Handle long click
            // Return true to indicate the click was handled

            val movies = realm.where(MovieDTO::class.java).findAll()
            val id = movies[adapterPosition]?.id
            val movieDTO = realm.where(MovieDTO::class.java).equalTo("id", id).findFirst()!!
            val movie = fromDTOtoObj(movieDTO)

            val context = view?.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("movie", movie)
            if (context != null) {
                context.startActivity(intent)
            }
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return this.realm.where(MovieDTO::class.java).findAll().size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = this.realm.where(MovieDTO::class.java).findAll()

        holder.itemTitle.text = movies[position]?.title
        holder.itemDirector.text = movies[position]?.director
        holder.itemDate.text = movies[position]?.date.toString()
        holder.itemRating.text = movies[position]?.rating.toString()

        holder.itemTrash.setOnClickListener{v: View ->
            val dialog = Dialog(v.context)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.delete_popup)
            val title = dialog.findViewById(R.id.titleLabel) as TextView
            var str = movies.get(position)?.title
            str += "?"
            title.text = str
            val yesView = dialog.findViewById(R.id.yesButton) as View
            val noView = dialog.findViewById(R.id.noButton) as View
            yesView.setOnClickListener {
                realm.beginTransaction()
                val id = movies[position]?.id
                val movie = realm.where(MovieDTO::class.java).equalTo("id", id).findFirst()!!
                movie.deleteFromRealm()
                realm.commitTransaction()
                notifyDataSetChanged()
                dialog.dismiss()
            }
            noView.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }


        holder.itemEdit.setOnClickListener{v: View ->
            val dialog = Dialog(v.context)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.edit_popup)
            val title = dialog.findViewById(R.id.titleLabel) as TextView
            var str = movies.get(position)?.title
            str += "?"
            title.text = str
            val yesView = dialog.findViewById(R.id.yesButton) as View
            val noView = dialog.findViewById(R.id.noButton) as View
            yesView.setOnClickListener {
                val context = v.context
                val intent = Intent(context, EditActivity::class.java)
                val id = movies[position]?.id
                val movieDTO = realm.where(MovieDTO::class.java).equalTo("id", id).findFirst()!!
                val movie = fromDTOtoObj(movieDTO)
                intent.putExtra("movie", movie)
                intent.putExtra("position", position)
                (context as Activity).startActivityForResult(intent, 5)
                notifyDataSetChanged()
                dialog.dismiss()
            }
            noView.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    fun fromDTOtoObj(movieDTO: MovieDTO): Movie {
        val title = movieDTO.title!!
        val director = movieDTO.director!!
        val date = movieDTO.date!!
        val rating = movieDTO.rating!!
        val review = movieDTO.review!!
        val id = movieDTO.id!!
        return Movie(id, title, director, date, rating, review)
    }

}