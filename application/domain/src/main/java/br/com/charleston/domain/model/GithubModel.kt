package br.com.charleston.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubModel(
    val name: String,
    val fullName: String,
    val private: String,
    val user: UserModel
) : Parcelable