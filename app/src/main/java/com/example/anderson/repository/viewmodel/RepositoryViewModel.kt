package com.example.anderson.repository.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.model.repository.RepoRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Anderson on 16/12/2018.
 */
class RepositoryViewModel : ViewModel() {

    private var listRepository = mutableListOf<Repository>()

    private var liveDataListRepository = MutableLiveData<List<Repository>>()

    private var showProgress = MutableLiveData<Boolean>()

    private var page = 1


    val repository = RepoRepository()


    fun callrequestRepository() {

        if (listRepository.size <= 0) {

            requestRepository()
        }

    }

     fun requestRepository() {


        repository.loadRepository(page)

                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({

                    respository ->
                    listRepository.add(respository)

                }, {

                    e ->
                    e.printStackTrace()
                    liveDataListRepository.value = listRepository
                    showProgress.value = true
                }, {

                    liveDataListRepository.value = listRepository
                    showProgress.value = true
                    page++

                })
    }


    fun getListRespository() : LiveData<List<Repository>> = liveDataListRepository
    fun showProgress(): LiveData<Boolean> = showProgress


}