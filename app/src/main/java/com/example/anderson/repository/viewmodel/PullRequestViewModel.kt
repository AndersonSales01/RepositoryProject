package com.example.anderson.repository.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import androidx.lifecycle.LifecycleObserver


import com.example.anderson.repository.model.entity.PullRequest
import com.example.anderson.repository.model.repository.RepoPullRequest
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.security.acl.Owner

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

    fun requestPullRequest(nameOwner: String, nameRepository: String) {

//        viewModelScope.launch {
//
//        }


        repository.loadPullRequest(nameOwner, nameRepository)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({

                    pullRquest ->
                    listPullRequest.add(pullRquest)

                }, {

                    e ->
                    e.printStackTrace()
                    liveDataListPullRequestRepository.value = listPullRequest
                    showProgress.value = true
                }, {

                    liveDataListPullRequestRepository.value = listPullRequest
                    showProgress.value = true

                })
    }

    fun getListPullRequests() : LiveData<List<PullRequest>> = liveDataListPullRequestRepository
    fun showProgress(): LiveData<Boolean> = showProgress


}