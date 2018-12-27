package br.com.charleston.data.cloud.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubResponse(
    @SerializedName("name") var name: String,
    @SerializedName("full_name") var fullName: String,
    @SerializedName("private") var private: Boolean,
    @SerializedName("owner") var owner: UserResponse
) : Serializable