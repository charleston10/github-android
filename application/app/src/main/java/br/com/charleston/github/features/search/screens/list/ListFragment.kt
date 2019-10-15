package br.com.charleston.github.features.search.screens.list

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.charleston.core.base.BaseFragment
import br.com.charleston.core.base.BaseViewModel
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.github.R
import br.com.charleston.github.databinding.FragmentListBinding
import br.com.charleston.github.features.search.adapters.GithubListAdapter
import br.com.charleston.github.features.search.adapters.ListListener

class ListFragment : BaseFragment<FragmentListBinding, BaseViewModel>(),
    ListListener {

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

    override fun onClickItem(githubModel: GithubModel, imageView: ImageView) {
        view?.let {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(githubModel)

            Navigation
                .findNavController(it)
                .navigate(action)
        }
    }

    private fun bindToolbar() {
        getViewDataBinding().toolbar.apply {
            title = context.getString(R.string.list_title)
        }

        (activity as AppCompatActivity)
            .setSupportActionBar(getViewDataBinding().toolbar)
    }

    private fun bindItems() {
        arguments?.let {
            val safeArgs = ListFragmentArgs.fromBundle(it)
            val items = safeArgs.items

            getViewDataBinding().list.apply {
                adapter = GithubListAdapter(items.toList(), this@ListFragment)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}