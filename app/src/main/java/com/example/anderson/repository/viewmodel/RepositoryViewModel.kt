package com.example.anderson.repository.viewmodel


import android.hardware.camera2.CameraManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anderson.repository.model.entity.Author
import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.model.entityrequest.Result
import com.example.anderson.repository.model.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

                val response = repository.loadRepository(page)

                Log.d("response", response.code().toString())

                if (response.isSuccessful) {

                    response.body().let { body ->

                        body.let { result ->

                            for (repo in result!!.repositoryList) {


                                if (repo.owner != null && repo.description != null) {

                                    val repository = Repository(repo.nameRepository, repo.fullName, repo.description,
                                            repo.numberForks, repo.numberStarts, Author(repo.owner.name, repo.owner.urlAvatar))

                                    listRepository.add(repository)

                                }
                            }

                            // Metodo postValue é usado para setar um valor no live data,quando está fora do contexto da Main Thread
                            liveDataListRepository.postValue(listRepository)
                            page++
                        }

                    }
                } else {

                    response.message()

                }

                showProgress.postValue(false)
            }
        }
    }


    fun getMoreItems( currentItems: Int, scrollOutItems: Int, totalItems : Int){

        if (currentItems + scrollOutItems >= totalItems) {
            requestRepository()
        }

    }


    fun getListRespository(): LiveData<List<Repository>> = liveDataListRepository
    fun showProgress(): LiveData<Boolean> = showProgress


}