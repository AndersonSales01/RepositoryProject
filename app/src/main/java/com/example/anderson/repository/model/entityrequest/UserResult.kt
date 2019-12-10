package com.example.anderson.repository.model.entityrequest

import com.google.gson.annotations.SerializedName


/**
 * Created by Anderson on 05/01/2019.
 */
class UserResult(@SerializedName("login")
                val name: String,
                 @SerializedName("avatar_url")
                val avatarURL: String
                 ) {

}