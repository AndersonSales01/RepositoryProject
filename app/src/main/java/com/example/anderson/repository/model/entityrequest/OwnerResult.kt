package com.example.anderson.repository.model.entityrequest

import com.google.gson.annotations.SerializedName

/**
 * Created by Anderson on 15/12/2018.
 */
class OwnerResult(@SerializedName("login")
                    val name: String,
                  @SerializedName("avatar_url")
                    val urlAvatar: String){
}