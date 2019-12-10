package com.example.anderson.repository.model.entityrequest

import com.google.gson.annotations.SerializedName

/**
 * Created by Anderson on 15/12/2018.
 */
class Result(@SerializedName("items")
                        val repositoryList: List<RepositoryResult>) {


}