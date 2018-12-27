package br.com.charleston.github.features.search.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.charleston.core.base.BaseFragment
import br.com.charleston.core.base.BaseViewModel
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.github.R
import br.com.charleston.github.databinding.FragmentListBinding
import br.com.charleston.github.features.search.adapters.GithubListAdapter

class ListFragment : BaseFragment<FragmentListBinding, BaseViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar()
        bindItems()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(BaseViewModel::class.java)
    }

    private fun bindToolbar() {
        getViewDataBinding().toolbar.apply {
            title = context.getString(R.string.list_title)
        }

        (activity as AppCompatActivity)
            .setSupportActionBar(getViewDataBinding().toolbar)
    }

    private fun bindItems() {
        if (arguments != null) {
            val safeArgs = ListFragmentArgs.fromBundle(arguments!!)
            val items = safeArgs.items

            getViewDataBinding().list.apply {
                adapter = GithubListAdapter(items.toList())
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}