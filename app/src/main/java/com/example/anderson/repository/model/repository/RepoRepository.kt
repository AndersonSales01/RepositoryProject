package com.example.anderson.repository.model.repository

import com.example.anderson.repository.model.api.RepositoryApi
import com.example.anderson.repository.model.api.endpoint.RepositoryEndPoint
import com.example.anderson.repository.model.entity.Author
import com.example.anderson.repository.model.entity.Repository
import rx.Observable

/**
 * Created by Anderson on 16/12/2018.
 */
class RepoRepository {

  //  lateinit var repositoryEndPoint: RepositoryEndPoint


//   suspend fun loadRepository(page:Int) = RepositoryApi.getInstance()
//
//            .create(RepositoryEndPoint::class.java)
//            .listRepository(page)
//            .flatMap { result ->
//                Observable.from(result.repositoryList)
//                        .flatMap { repositoryResult ->
//                            Observable.just(
//                                    Repository(repositoryResult.nameRepository,repositoryResult.fullName, repositoryResult.description,
//                                            repositoryResult.numberForks, repositoryResult.numberStarts, Author(repositoryResult.owner.name,repositoryResult.owner.urlAvatar)))
//                        }
//
//
//            }!!

    suspend fun loadRepository(page:Int) = RepositoryApi.getInstance()
            .create(RepositoryEndPoint::class.java)
            .listRepository(page)



}