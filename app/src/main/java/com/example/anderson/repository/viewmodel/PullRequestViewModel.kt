package com.example.anderson.repository.viewmodel


import androidx.lifecycle.*


import com.example.anderson.repository.model.entity.PullRequest
import com.example.anderson.repository.model.entity.User
import com.example.anderson.repository.model.repository.RepoPullRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by Anderson on 05/01/2019.
 */
class PullRequestViewModel : ViewModel(), LifecycleObserver {

    private var listPullRequest = mutableListOf<PullRequest>()

    private var liveDataListPullRequestRepository = MutableLiveData<List<PullRequest>>()
    private var showProgress = MutableLiveData<Boolean>()


    val repository = RepoPullRequest()


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

                delay(500)

                val response = repository.loadPullRequest(nameOwner, nameRepository)

                if (response.isSuccessful) {

                    response.body().let { listOfPullRequest ->


                        listOfPullRequest.let { pullRequests ->

                            if (pullRequests != null) {

                                for (pullRequest in pullRequests) {

                                    val pullRequestObj = PullRequest(pullRequest.title, pullRequest.body, pullRequest.dataCreatePullRequest,
                                            pullRequest.urlPullRequest, User(pullRequest.user.name, pullRequest.user.avatarURL))

                                    listPullRequest.add(pullRequestObj)

                                }

                                liveDataListPullRequestRepository.postValue(listPullRequest)
                                showProgress.postValue(false)

                            }
                        }
                    }
                }
            }
        }
    }


    fun getListPullRequests(): LiveData<List<PullRequest>> = liveDataListPullRequestRepository
    fun showProgress(): LiveData<Boolean> = showProgress


}