package br.com.charleston.github.features.search.screens.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.charleston.core.base.BaseFragment
import br.com.charleston.core.base.BaseViewModel
import br.com.charleston.github.R
import br.com.charleston.github.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindModel()
        bindToolbar()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(BaseViewModel::class.java)
    }

    private fun bindModel() {
        arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            val model = safeArgs.model
            getViewDataBinding().model = model
        }
    }

    private fun bindToolbar() {
        getViewDataBinding().toolbar.apply {
            title = context.getString(R.string.detail_title)
        }

        (activity as AppCompatActivity).let {
            it.setSupportActionBar(getViewDataBinding().toolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }
}