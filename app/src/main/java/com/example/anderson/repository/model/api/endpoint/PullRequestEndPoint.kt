package com.example.anderson.repository.model.api.endpoint

import com.example.anderson.repository.model.entityrequest.PullRequestDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Anderson on 05/01/2019.
 */
interface PullRequestEndPoint {

    @GET("repos/{login}/{name}/pulls")
     fun callPullRequest(@Path("login") loginOwner: String, @Path("name") nameRepository: String): Deferred<List<PullRequestDTO>>
}