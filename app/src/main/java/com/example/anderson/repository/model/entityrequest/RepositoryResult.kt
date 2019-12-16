package com.example.anderson.repository.model.entityrequest

import com.google.gson.annotations.SerializedName

/**
 * Created by Anderson on 15/12/2018.
 */
class RepositoryResult(@SerializedName("name")
                         val nameRepository: String = "",
                       @SerializedName("full_name")
                       val fullName: String  = "",
                       val description: String  = "",
                       @SerializedName("forks")
                         val numberForks: Int = 0,
                       @SerializedName("watchers")
                         val numberStarts: Int = 0,
                       @SerializedName("owner")
                         val owner: OwnerResult) {
}