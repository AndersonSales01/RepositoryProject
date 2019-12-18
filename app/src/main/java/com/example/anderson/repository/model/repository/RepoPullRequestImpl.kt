package com.example.anderson.repository.model.repository

import android.util.Log
import com.example.anderson.repository.model.api.RepositoryApi
import com.example.anderson.repository.model.api.endpoint.PullRequestEndPoint
import com.example.anderson.repository.model.entity.PullRequest
import com.example.anderson.repository.model.entity.User
import com.example.anderson.repository.model.entityrequest.PullRequestResult
import com.example.anderson.repository.model.repository.interfaces.IRepoPullRquest
import retrofit2.Response
import rx.Observable
import java.util.ArrayList

/**
 * Created by Anderson on 05/01/2019.
 */
class RepoPullRequestImpl : IRepoPullRquest {


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


    override suspend fun loadPullRequest(nameOwner: String, nameRepository: String): List<PullRequest> {

        val listPullRequest = ArrayList<PullRequest>()

        val response = RepositoryApi.getInstance().create(PullRequestEndPoint::class.java).callPullRequest(nameOwner, nameRepository).await()

        Log.d("listresult", response.toString())

        response.let {

            if (response.isNotEmpty()) {

                for (pullRequest in response) {

                    val pullRequestObj = PullRequest(pullRequest.title, pullRequest.body, pullRequest.dataCreatePullRequest,
                            pullRequest.urlPullRequest, User(pullRequest.user.name, pullRequest.user.avatarURL))

                    listPullRequest.add(pullRequestObj)

                }
            }
        }


        return listPullRequest
    }


}