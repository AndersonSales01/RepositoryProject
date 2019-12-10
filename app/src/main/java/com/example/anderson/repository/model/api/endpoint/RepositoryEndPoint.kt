package com.example.anderson.repository.model.api.endpoint

import com.example.anderson.repository.model.entityrequest.Result
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Anderson on 16/12/2018.
 */
interface RepositoryEndPoint {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun  listRepository( @Query("page") page: Int ): Observable<Result>
}