package com.example.geo.activityLists

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.geo.ExamApp
import com.example.geo.SectionsActivity
import com.example.geo.adapter.StatsAdapter
import com.example.geo.databinding.ExamListLayoutBinding
import com.example.geo.domain.Exam
import com.example.geo.model.MainModel
import com.example.geo.network.Manager
import com.google.android.material.snackbar.Snackbar
import ro.ubbcluj.cs.books.utils.logd


class StatsExamListActivity: AppCompatActivity() {
    private var adapter: StatsAdapter? = null
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
           showDialog()
        }
        showDialog()
        setupRecyclerView(binding.recyclerView)
        observeModel()
    }

    private fun showDialog(){
        val taskEditText = EditText(this)
        val dialog: android.app.AlertDialog? = android.app.AlertDialog.Builder(this)
            .setTitle("Group Exams")
            .setMessage("Which group exams do you wanna see?")
            .setView(taskEditText)
            .setPositiveButton("Get Exams", DialogInterface.OnClickListener { dialog, which ->
                val task = taskEditText.text.toString()
                model.fetchStatsExamsFromNetwork(application as ExamApp, task)
            })
            .setNegativeButton("Cancel", null)
            .create()
        dialog?.show()
    }

    private fun observeModel() {
        model.loading.observe { displayLoading(it) }
        model.books.observe { displayExams(it ?: emptyList()) }
        model.message.observe { showError(it) }
    }

    private fun displayExams(exams: List<Exam>) {
        var sortedList = exams.sortedWith(compareBy({ it.type }))
        adapter?.setData(sortedList)
    }

    private fun displayLoading(loading: Boolean?) {
        logd("displayLoading: $loading")
        binding.progress.visibility = if (loading!!) View.VISIBLE else View.GONE
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
        observe(this@StatsExamListActivity, { observe(it) })


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = StatsAdapter()
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