package com.example.anderson.repository.model.repository.interfaces

import com.example.anderson.repository.model.entity.PullRequest

interface IRepoPullRquest {

    suspend fun loadPullRequest(nameOwner: String, nameRepository: String): List<PullRequest>
}