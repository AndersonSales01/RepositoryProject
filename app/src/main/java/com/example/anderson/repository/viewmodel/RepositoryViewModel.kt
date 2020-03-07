package com.example.anderson.repository.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.anderson.repository.model.entity.Author
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.model.repository.RepoRepositoryImpl
import com.github.kittinunf.result.coroutines.SuspendableResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Anderson on 16/12/2018.
 */
class RepositoryViewModel(val repository: RepoRepositoryImpl) : ViewModel(), LifecycleObserver {

    private var listRepository = mutableListOf<Repository>()

    private var liveDataListRepository = MutableLiveData<List<Repository>>()

    private var showProgress = MutableLiveData<Boolean>()

    private var page = 1


    fun callrequestRepository() {

        if (listRepository.size <= 0) {

            requestRepository()
        }

    }

//     fun requestRepository() {
//
//         viewModelScope.launch {
//             withContext(Dispatchers.IO) {
//
//                 repository.loadRepository(page)
//
//                         .subscribeOn(Schedulers.io())
//
//                         .observeOn(AndroidSchedulers.mainThread())
//
//                         .subscribe({
//
//                             respository ->
//                             listRepository.add(respository)
//
//                         }, {
//
//                             e ->
//                             e.printStackTrace()
//                             liveDataListRepository.value = listRepository
//                             showProgress.value = true
//                         }, {
//
//                             liveDataListRepository.value = listRepository
//                             showProgress.value = true
//                             page++
//
//                         })
//             }
//         }
//
//
//    }

    private fun requestRepository() {

        showProgress.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {


                val response = SuspendableResult.of<List<Repository>, Exception> {

                    repository.loadRepository(page)
                }
                val onSuccess: suspend (List<Repository>) -> Unit = { list ->
                    liveDataListRepository.postValue(list)
                    page++
                }
                val onFailure: suspend (Exception) -> Unit = { error ->
                    Log.d("Error Request", error.toString())
                }


                response.fold(onSuccess, onFailure)


                showProgress.postValue(false)
            }
        }
    }


    fun getMoreItems(currentItems: Int, scrollOutItems: Int, totalItems: Int) {

        if (currentItems + scrollOutItems >= totalItems) {
            requestRepository()
        }

    }




    fun getListRespository(): LiveData<List<Repository>> = liveDataListRepository
    fun showProgress(): LiveData<Boolean> = showProgress


}