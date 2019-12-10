package com.example.anderson.repository.model.api.endpoint

import com.example.anderson.repository.model.entityrequest.PullRequestResult
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by Anderson on 05/01/2019.
 */
interface PullRequestEndPoint {

    @GET("repos/{login}/{name}/pulls")
    fun callPullRequest(@Path("login") loginOwner: String, @Path("name") nameRepository: String): Observable<List<PullRequestResult>>
}