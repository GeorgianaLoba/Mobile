//package com.example.geo.adapter
//
//import android.app.AlertDialog
//import android.content.DialogInterface
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.geo.R
//import com.example.geo.activityLists.StudentExamListActivity
//import com.example.geo.domain.Exam
//
//
//class StudentAdapter: RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
//    private var mValues = mutableListOf<Exam>()
//
//    fun setData(exams: List<Exam>) {
//        mValues.clear()
//        mValues.addAll(exams)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_content, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val exam = mValues[position]
//        holder.mItem = exam
//        holder.mIdView.text = exam.id.toString()
//        holder.mNameView.text = exam.name
//        holder.mGroupView.text = exam.group
//        holder.mTypeView.text = exam.type
//
//
//        holder.mView.setOnClickListener { v ->
//            val builder: AlertDialog.Builder = AlertDialog.Builder(v.context)
//            val name = exam.name
//            builder.setTitle("Join $name exam?")
//            builder.setMessage("Are you sure?")
//            builder.setPositiveButton(
//                "YES",
//                DialogInterface.OnClickListener { dialog, which ->
//                    (v.context as StudentExamListActivity).joinExam(exam)
//                    dialog.dismiss()
//                })
//            builder.setNegativeButton(
//                "NO",
//                DialogInterface.OnClickListener { dialog, which -> // Do nothing
//                    dialog.dismiss()
//                })
//
//            val alert: AlertDialog = builder.create()
//            alert.show()
//        }
//    }
//    override fun getItemCount(): Int {
//        return mValues.size
//    }
//
//    inner class ViewHolder internal constructor(internal val mView: View) : RecyclerView.ViewHolder(
//        mView
//    ) {
//        internal val mIdView: TextView = mView.findViewById(R.id.id)
//        internal val mNameView: TextView = mView.findViewById(R.id.name)
//        internal val mGroupView: TextView = mView.findViewById(R.id.group)
//        internal val mTypeView: TextView = mView.findViewById(R.id.type)
//        internal var mItem: Exam? = null
//
//        override fun toString(): String {
//            return "${super.toString()} '${mNameView.text}' '${mGroupView.text}' '${mTypeView.text}'"
//        }
//    }
//}