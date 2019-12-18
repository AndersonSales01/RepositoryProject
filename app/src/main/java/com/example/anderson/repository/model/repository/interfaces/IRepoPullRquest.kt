package com.example.anderson.repository.model.repository.interfaces

import com.example.anderson.repository.model.entity.PullRequest
import com.example.anderson.repository.model.entityrequest.PullRequestResult
import retrofit2.Response

interface IRepoPullRquest {

    suspend fun loadPullRequest(nameOwner: String, nameRepository: String): List<PullRequest>
}