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


class RecyclerAdapter(private val movies: MutableList<Movie>):
RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var context: Context? = null
    private final var intent: Intent? = null

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
            val context = view?.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("movie", movies[adapterPosition])
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
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = movies[position].title
        holder.itemDirector.text = movies[position].director
        holder.itemDate.text = movies[position].date.toString()
        holder.itemRating.text = movies[position].rating.toString()

        holder.itemTrash.setOnClickListener{v: View ->
            val dialog = Dialog(v.context)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.delete_popup)
            val title = dialog.findViewById(R.id.titleLabel) as TextView
            var str = movies.get(position).title
            str += "?"
            title.text = str
            val yesView = dialog.findViewById(R.id.yesButton) as View
            val noView = dialog.findViewById(R.id.noButton) as View
            yesView.setOnClickListener {
                movies.removeAt(position)
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
            var str = movies.get(position).title
            str += "?"
            title.text = str
            val yesView = dialog.findViewById(R.id.yesButton) as View
            val noView = dialog.findViewById(R.id.noButton) as View
            yesView.setOnClickListener {
                val context = v.context
                val intent = Intent(context, EditActivity::class.java)
                intent.putExtra("movie", movies[position])
                intent.putExtra("position", position)
                (context as Activity).startActivityForResult(intent, 5)
                notifyDataSetChanged()
                dialog.dismiss()
            }
            noView.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }



}