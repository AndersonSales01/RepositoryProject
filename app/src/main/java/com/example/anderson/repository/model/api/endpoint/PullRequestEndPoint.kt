package com.example.anderson.repository.model.api.endpoint

import com.example.anderson.repository.model.entityrequest.PullRequestResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by Anderson on 05/01/2019.
 */
interface PullRequestEndPoint {

    @GET("repos/{login}/{name}/pulls")
    suspend fun callPullRequest(@Path("login") loginOwner: String, @Path("name") nameRepository: String): Response<List<PullRequestResult>>
}