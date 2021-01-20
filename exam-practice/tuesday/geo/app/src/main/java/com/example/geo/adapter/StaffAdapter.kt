package com.example.geo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geo.R
import com.example.geo.domain.Rule

class StaffAdapter: RecyclerView.Adapter<StaffAdapter.ViewHolder>() {
    private var mValues = mutableListOf<Rule>()

    fun setData(objects: List<Rule>) {
        mValues.clear()
        mValues.addAll(objects)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = mValues[position]
        holder.mItem = obj
        holder.mIdView.text = obj.id.toString()
        holder.mNameView.text = obj.name
        holder.mLevelView.text = obj.status

//        holder.mView.setOnClickListener { v ->
//            val context = v.context
//            val intent = Intent(context, DetailExam::class.java)
//            intent.putExtra(DetailExamFragment.ARG_ITEM_ID, exam.id)
//            context.startActivity(intent)
            //i see the details of an exam, by performing an api call
            //for sending yourself the exam, do as below
//            val args = Bundle()
//            args.putInt(DetailExamFragment.ARG_ITEM_ID, exam.id)
//            args.putString(DetailExamFragment.ARG_ITEM_NAME, exam.name)
//            args.putString(DetailExamFragment.ARG_ITEM_STATUS, exam.status)
//            args.putString(DetailExamFragment.ARG_ITEM_DETAILS, exam.details)
//            args.putString(DetailExamFragment.ARG_ITEM_GROUP, exam.group)
//            args.putInt(DetailExamFragment.ARG_ITEM_STUDENTS, exam.students!!)
//            args.putString(DetailExamFragment.ARG_ITEM_TYPE, exam.type)
//            intent.putExtras(args)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder internal constructor(internal val mView: View) : RecyclerView.ViewHolder(mView) {
        internal val mIdView: TextView = mView.findViewById(R.id.id)
        internal val mNameView: TextView = mView.findViewById(R.id.name)
        internal val mLevelView: TextView = mView.findViewById(R.id.level)
        internal var mItem: Rule? = null

        override fun toString(): String {
            return "${super.toString()} '${mNameView.text}' '${mLevelView.text}'"
        }
    }
}