package br.com.charleston.github.features.search.adapters

import android.widget.ImageView
import br.com.charleston.domain.model.GithubModel

interface ListListener {
    fun onClickItem(githubModel: GithubModel, imageView: ImageView)
}