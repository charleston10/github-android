package br.com.charleston.domain.model

data class GithubModel(
    val name: String,
    val fullName: String,
    val user: UserModel
)