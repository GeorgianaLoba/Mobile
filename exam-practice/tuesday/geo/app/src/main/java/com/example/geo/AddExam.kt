//package com.example.geo
//
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import com.example.geo.databinding.NewExamLayoutBinding
//import com.example.geo.domain.Exam
//import com.example.geo.model.MainModel
//
//class AddExam: AppCompatActivity(){
//    private lateinit var model: MainModel
//    private lateinit var binding: NewExamLayoutBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = NewExamLayoutBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        model = ViewModelProvider(this).get(MainModel::class.java)
//        binding.save.setOnClickListener{
//            val app: ExamApp = application as ExamApp
//            model.addExam(app, Exam(0, binding.name.text.toString(), binding.group.text.toString(),
//            binding.details.text.toString(), binding.status.text.toString(), binding.students.text.toString().toInt(), binding.type.text.toString()))
//            finish()
//        }
//    }
//}