package com.example.anderson.repository.model.repository.interfaces

import com.example.anderson.repository.model.entity.Repository
import com.example.anderson.repository.model.entityrequest.ResponseDTO
import retrofit2.Response

interface IRepoRepository {

    suspend fun loadRepository(page:Int) : List<Repository>
}