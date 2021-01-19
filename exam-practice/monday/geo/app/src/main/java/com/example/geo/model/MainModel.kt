package com.example.geo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geo.ExamApp
import com.example.geo.domain.Exam
import com.example.geo.service.ExamService
import com.example.geo.service.ServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ro.ubbcluj.cs.books.utils.logd

class MainModel: ViewModel() {
    private val service: ExamService = ServiceFactory
        .createRetrofitService(ExamService::class.java, ExamService.SERVICE_ENDPOINT)

    private val mutableExams = MutableLiveData<List<Exam>>().apply { value = emptyList() }
    private val mutableLoading = MutableLiveData<Boolean>().apply { value = false }
    private val mutableMessage = MutableLiveData<String>()
    private var mutableExam = MutableLiveData<Exam>()

    val books: LiveData<List<Exam>> = mutableExams
    val loading: LiveData<Boolean> = mutableLoading
    val message: LiveData<String> = mutableMessage
    val exam: LiveData<Exam> = mutableExam

    fun fetchExamsFromNetwork(app: ExamApp) {
        viewModelScope.launch {
            mutableLoading.value = true
            try {
                mutableExams.value = service.getExams()
                launch(Dispatchers.IO) {
                    app.db.examDao.deleteExams()
                    app.db.examDao.addExams(books.value!!)
                }
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while retrieving the data: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun fetchDraftExamsFromNetwork(app: ExamApp) {
        viewModelScope.launch {
            mutableLoading.value = true
            try {
                mutableExams.value = service.getDraftExams()
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while retrieving the data: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun fetchStatsExamsFromNetwork(app: ExamApp, group: String) {
        viewModelScope.launch {
            mutableLoading.value = true
            try {
                mutableExams.value = service.getStatsExams(group)
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while retrieving the data: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun fetchExams(app: ExamApp) {
        mutableLoading.value = true
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val numberOfExams = app.db.examDao.numberOfExams
                if (numberOfExams <= 0) {
                    fetchExamsFromNetwork(app)
                }
            }
        } catch (e: Exception) {
            mutableMessage.value = "Received an error while retrieving local data: ${e.message}"
        } finally {
            mutableLoading.value = false
        }
    }

    fun addExam(app: ExamApp, exam: Exam){
        mutableLoading.value = true
        viewModelScope.launch {
            try {
                service.addExam(exam)
                launch(Dispatchers.IO) {
                    app.db.examDao.addExam(exam)
                }
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while adding the data: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun getExam(app: ExamApp, id: Int) {
        mutableLoading.value = true
        viewModelScope.launch {
            try {
                val exam = service.getExam(id)
                logd("in model, exam fetched: $exam")
                mutableExam.value = exam
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while fetching the exam: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun joinExam(app: ExamApp, exam: Exam) {
        mutableLoading.value = true
        viewModelScope.launch {
            try {
                val exam = service.joinExam(exam)
                logd("in model, exam joined: $exam")
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while joining the exam: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }
}