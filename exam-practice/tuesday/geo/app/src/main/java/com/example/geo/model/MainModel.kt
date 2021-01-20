package com.example.geo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geo.MainApp
import com.example.geo.domain.Rule
import com.example.geo.service.Service
import com.example.geo.service.ServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ro.ubbcluj.cs.books.utils.logd

class MainModel: ViewModel() {
    private val service: Service = ServiceFactory
        .createRetrofitService(Service::class.java, Service.SERVICE_ENDPOINT)

    private val mutableObjects = MutableLiveData<List<Rule>>().apply { value = emptyList() }
    private val mutableLoading = MutableLiveData<Boolean>().apply { value = false }
    private val mutableMessage = MutableLiveData<String>()
    private var mutableObj = MutableLiveData<Rule>()

    val objects: LiveData<List<Rule>> = mutableObjects
    val loading: LiveData<Boolean> = mutableLoading
    val message: LiveData<String> = mutableMessage
    val obj: LiveData<Rule> = mutableObj

    fun fetchObjectsFromNetwork(app: MainApp) {
        viewModelScope.launch {
            mutableLoading.value = true
            try {
                mutableObjects.value = service.getRules()
                launch(Dispatchers.IO) {
                    app.db.objDao.deleteObjects()
                    app.db.objDao.addObjects(objects.value!!)
                }
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while retrieving the data: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun fetchObjects(app: MainApp) {
        mutableLoading.value = true
        try {
            GlobalScope.launch(Dispatchers.IO) {
                val numberOfObjects = app.db.objDao.numberOfObjects
                if (numberOfObjects <= 0) {
                    fetchObjectsFromNetwork(app)
                }
            }
        } catch (e: Exception) {
            mutableMessage.value = "Received an error while retrieving local data: ${e.message}"
        } finally {
            mutableLoading.value = false
        }
    }

    fun addObj(app: MainApp, rule: Rule){
        mutableLoading.value = true
        viewModelScope.launch {
            try {
                service.addRule(rule)
                launch(Dispatchers.IO) {
                    app.db.objDao.addObj(rule)
                }
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while adding the data: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

    fun getRule(app: MainApp, id: Int) {
        mutableLoading.value = true
        viewModelScope.launch {
            try {
                val obj = service.getRule(id)
                logd("in model, obj fetched by id: $obj")
                mutableObj.value = obj
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while fetching the object by id: ${e.message}"
            } finally {
                mutableLoading.value = false
            }
        }
    }

}