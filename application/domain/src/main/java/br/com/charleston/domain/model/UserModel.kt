package br.com.charleston.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val avatarUrl: String,
    val name: String
) : Parcelable