package com.example.geo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geo.DetailExam
import com.example.geo.DetailExamFragment
import com.example.geo.R
import com.example.geo.domain.Exam

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var mValues = mutableListOf<Exam>()

    fun setData(exams: List<Exam>) {
        mValues.clear()
        mValues.addAll(exams)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exam_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exam = mValues[position]
        holder.mItem = exam
        holder.mIdView.text = exam.id.toString()
        holder.mNameView.text = exam.name
        holder.mGroupView.text = exam.group
        holder.mTypeView.text = exam.type
        holder.mView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, DetailExam::class.java)
            intent.putExtra(DetailExamFragment.ARG_ITEM_ID, exam.id)
            context.startActivity(intent)
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
        }
    }
    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder internal constructor(internal val mView: View) : RecyclerView.ViewHolder(mView) {
        internal val mIdView: TextView = mView.findViewById(R.id.id)
        internal val mNameView: TextView = mView.findViewById(R.id.name)
        internal val mGroupView: TextView = mView.findViewById(R.id.group)
        internal val mTypeView: TextView = mView.findViewById(R.id.type)
        internal var mItem: Exam? = null

        override fun toString(): String {
            return "${super.toString()} '${mNameView.text}' '${mGroupView.text}' '${mTypeView.text}'"
        }
    }
}