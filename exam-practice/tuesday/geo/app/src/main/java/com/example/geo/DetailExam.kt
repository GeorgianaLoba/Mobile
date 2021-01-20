//package com.example.geo
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.MenuItem
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.core.app.NavUtils
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModelProvider
//import com.example.geo.activityLists.ExamListActivity
//import com.example.geo.databinding.DetailExamLayoutBinding
//import com.example.geo.domain.Exam
//import com.example.geo.model.MainModel
//import com.google.android.material.snackbar.Snackbar
//
//class DetailExam: AppCompatActivity() {
//    private lateinit var binding: DetailExamLayoutBinding //the layout specification
//    private lateinit var model: MainModel //the operations are made through the model always
//    private  var id: Int? = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DetailExamLayoutBinding.inflate(layoutInflater)
//        setContentView(binding.root) //setting the layout
//        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
//        setSupportActionBar(toolbar)
//        model = ViewModelProvider(this).get(MainModel::class.java)
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//        // Show the Up button in the action bar.
//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//        if (savedInstanceState == null) {
//            id = intent.getIntExtra(DetailExamFragment.ARG_ITEM_ID, 0)
//            getExam()
//        }
//    }
//    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
//        observe(this@DetailExam, { observe(it) })
//
//    private fun getExam(){
//        val app: ExamApp = application as ExamApp
//        model.getExam(app, id!!)
//        model.exam.observe{ setExam(it)}
//    }
//
//    private fun setExam(exam: Exam?){
//        if (exam != null) {
//            binding.name.text = exam.name
//            binding.group.text = exam.group
//            binding.status.text = exam.status
//            binding.details.text = exam.details
//            binding.students.text = exam.students.toString()
//            binding.type.text = exam.type
//        }
//        else{
//            Snackbar.make(binding.eventDetailContainer, "No internet", Snackbar.LENGTH_INDEFINITE)
//                .setAction("RETRY") { getExam() }.show()
//        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == android.R.id.home) {
//            NavUtils.navigateUpTo(this, Intent(this, ExamListActivity::class.java))
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//}