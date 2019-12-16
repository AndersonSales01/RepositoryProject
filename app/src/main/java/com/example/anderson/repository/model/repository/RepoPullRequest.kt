package com.example.anderson.repository.model.repository

import com.example.anderson.repository.model.api.RepositoryApi
import com.example.anderson.repository.model.api.endpoint.PullRequestEndPoint
import com.example.anderson.repository.model.entity.PullRequest
import com.example.anderson.repository.model.entity.User
import rx.Observable

/**
 * Created by Anderson on 05/01/2019.
 */
class RepoPullRequest {


//   suspend fun loadPullRequest(nameOwner: String, nameRepository: String) = RepositoryApi.getInstance()
//
//            .create(PullRequestEndPoint::class.java)
//            .callPullRequest(nameOwner, nameRepository)
//            .flatMap { pullRequestResult ->
//                Observable.from(pullRequestResult)
//                        .flatMap { pullRquest ->
//                            Observable.just(
//                                    PullRequest(pullRquest.title, pullRquest.body, pullRquest.dataCreatePullRequest,pullRquest.urlPullRequest, User(pullRquest.user.name, pullRquest.user.avatarURL)))
//                        }
//
//
//            }!!


    suspend fun loadPullRequest(nameOwner: String, nameRepository: String) = RepositoryApi.getInstance()
            .create(PullRequestEndPoint::class.java)
            .callPullRequest(nameOwner, nameRepository)

}