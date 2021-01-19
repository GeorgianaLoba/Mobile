package com.example.geo.activityLists

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.geo.ExamApp
import com.example.geo.SectionsActivity
import com.example.geo.adapter.StudentAdapter
import com.example.geo.databinding.ExamListLayoutBinding
import com.example.geo.domain.Exam
import com.example.geo.model.MainModel
import com.example.geo.network.Manager
import com.google.android.material.snackbar.Snackbar
import ro.ubbcluj.cs.books.utils.logd

class StudentExamListActivity: AppCompatActivity() {
    private var adapter: StudentAdapter? = null
    private lateinit var manager: Manager
    private lateinit var model: MainModel
    private lateinit var binding: ExamListLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ExamListLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(MainModel::class.java)
        manager = Manager()
        setSupportActionBar(binding.toolbar)
        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = title

        binding.refresh.setOnClickListener {
            model.fetchDraftExamsFromNetwork(application as ExamApp)
        }
        model.fetchDraftExamsFromNetwork(application as ExamApp)
        setupRecyclerView(binding.recyclerView)
        observeModel()
    }

    private fun observeModel() {
        model.loading.observe { displayLoading(it) }
        model.books.observe { displayExams(it ?: emptyList()) }
        model.message.observe { showError(it) }
    }

    fun joinExam(exam: Exam){
        logd("before joining exam, in list with id: $exam")
        model.joinExam(application as ExamApp, exam)
    }

    private fun displayExams(exams: List<Exam>) {
        adapter?.setData(exams)
    }

    private fun displayLoading(loading: Boolean?) {
        logd("displayLoading: $loading")
        binding.progress.visibility = if (loading!!) View.VISIBLE else View.GONE
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
        observe(this@StudentExamListActivity, { observe(it) })


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = StudentAdapter()
        recyclerView.adapter = adapter
    }

    private fun showError(message: String?) {
        binding.progress.visibility = View.GONE
        var errorMessage = "Unknown error"
        if (message != null) {
            errorMessage = message
        }
        Snackbar.make(binding.recyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE).show()
    }

    //function for the return arrow action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, Intent(this, SectionsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}