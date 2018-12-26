package br.com.charleston.data.cloud.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResponse(
    @SerializedName("total_count") val total : Long,
    @SerializedName("items") val items : List<GithubResponse>
) : Serializable