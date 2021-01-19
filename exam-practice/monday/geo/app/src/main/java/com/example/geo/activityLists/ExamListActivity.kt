package com.example.geo.activityLists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.geo.AddExam
import com.example.geo.ExamApp
import com.example.geo.SectionsActivity
import com.google.android.material.snackbar.Snackbar

import com.example.geo.databinding.ExamListLayoutBinding
import com.example.geo.adapter.MyAdapter
import com.example.geo.domain.Exam
import com.example.geo.model.MainModel
import com.example.geo.network.Manager
import ro.ubbcluj.cs.books.utils.logd


class ExamListActivity: AppCompatActivity() {
    private var adapter: MyAdapter? = null
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
            model.fetchExamsFromNetwork(application as ExamApp)
        }

        binding.fab.setOnClickListener{
            val intent = Intent(application, AddExam::class.java)
            startActivityForResult(intent, 3) //3 add
        }

        setupRecyclerView(binding.recyclerView)
        observeModel()
        loadExams()
    }

    private fun observeModel() {
        model.loading.observe { displayLoading(it) }
        model.books.observe { displayExams(it ?: emptyList()) }
        model.message.observe { showError(it) }
    }

    private fun displayExams(exams: List<Exam>) {
        adapter?.setData(exams)
    }

    private fun displayLoading(loading: Boolean?) {
        logd("displayLoading: $loading")
        binding.progress.visibility = if (loading!!) View.VISIBLE else View.GONE
    }

    private fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
        observe(this@ExamListActivity, { observe(it) })

    private fun loadExams() {
        model.fetchExams(application as ExamApp)
    }
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter = MyAdapter()
        (application as ExamApp).db.examDao.exams
            .observe(this, { exams ->
                if (exams != null) {
                    adapter!!.setData(exams)
                }
            })
        recyclerView.adapter = adapter
    }

    private fun showError(message: String?) {
        binding.progress.visibility = View.GONE
        var errorMessage = "Unknown error"
        if (message != null) {
            errorMessage = message
        }
        Snackbar.make(binding.recyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction("RETRY") { loadExams() }.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "added!", Toast.LENGTH_SHORT).show()
                logd("Back in main activity")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, Intent(this, SectionsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}