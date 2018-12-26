package br.com.charleston.data.cloud.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubResponse(
    @SerializedName("name") var name: String,
    @SerializedName("fullName") var fullName: String,
    @SerializedName("topics") var tags: List<String>,
    @SerializedName("owner") var owner: UserResponse
) : Serializable