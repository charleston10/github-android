package br.com.charleston.data.cloud.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val name: String
) : Serializable