package com.example.anderson.repository.viewmodel


import android.util.Log
import androidx.lifecycle.*


import com.example.anderson.repository.model.entity.PullRequest
import com.example.anderson.repository.model.repository.RepoPullRequestImpl
import com.github.kittinunf.result.coroutines.SuspendableResult
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Anderson on 05/01/2019.
 */
class PullRequestViewModel(private val repository: RepoPullRequestImpl) : ViewModel(), LifecycleObserver {

    private var listPullRequest = mutableListOf<PullRequest>()

    private var liveDataListPullRequestRepository = MutableLiveData<List<PullRequest>>()
    private var showProgress = MutableLiveData<Boolean>()

    private var result  = MutableLiveData<Int>()


    fun callRequestPullResquest(nameOwner: String, nameRepository: String) {

        if (nameOwner != null && nameOwner != "" && nameRepository != null && nameRepository != "") {

            requestPullRequest(nameOwner, nameRepository)

        }

    }


//    fun requestPullRequest(nameOwner: String, nameRepository: String) {
//
//        viewModelScope.launch {
//
//            withContext(Dispatchers.IO){
//
//
//
//
//
//
//                repository.loadPullRequest(nameOwner, nameRepository)
//                        .subscribeOn(Schedulers.io())
//
//                        .observeOn(AndroidSchedulers.mainThread())
//
//                        .subscribe({
//
//                            pullRquest ->
//                            listPullRequest.add(pullRquest)
//
//                        }, {
//
//                            e ->
//                            e.printStackTrace()
//                            liveDataListPullRequestRepository.value = listPullRequest
//                            showProgress.value = true
//                        }, {
//
//                            liveDataListPullRequestRepository.value = listPullRequest
//                            showProgress.value = true
//
//                        })
//
//            }
//
//        }


    private fun requestPullRequest(nameOwner: String, nameRepository: String) {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {

                showProgress.postValue(true)


                SuspendableResult.of<List<PullRequest>, Exception> {

                    repository.loadPullRequest(nameOwner, nameRepository)

                }.fold(
                        success = { list ->

                            liveDataListPullRequestRepository.postValue(list)
                            showProgress.postValue(false)

                        },
                        failure = { error ->

                            Log.d("Error Request", error.toString())
                            showProgress.postValue(false)

                        }
                )
            }
        }
    }


    fun getListPullRequests(): LiveData<List<PullRequest>> = liveDataListPullRequestRepository
    fun showProgress(): LiveData<Boolean> = showProgress

    fun Soma(valor1: Int, valor2: Int) {

        result.postValue(valor1 + valor2)
    }
    fun diminuicao(valor1: Int, valor2: Int) {

        result.postValue( valor1 - valor2)
    }

    fun getResultCalculate(): LiveData<Int> = result

}