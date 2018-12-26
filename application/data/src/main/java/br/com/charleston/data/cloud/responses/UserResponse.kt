package br.com.charleston.data.cloud.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(
    @SerializedName("avatar") val urlAvatar: String,
    @SerializedName("name") val name: String
) : Serializable