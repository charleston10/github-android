package br.com.charleston.github.features.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.github.R
import br.com.charleston.github.databinding.ItemListBinding

class GithubListAdapter(
    private val items: List<GithubModel>,
    private val listener: ListListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val heroesHolder = (holder as ListViewHolder)
        heroesHolder.bind(item)
    }

    inner class ListViewHolder(
        private val viewDataBinding: ItemListBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(data: GithubModel) {
            viewDataBinding.setVariable(BR.model, data)
            viewDataBinding.executePendingBindings()

            viewDataBinding.root.setOnClickListener {
                listener.onClickItem((data), viewDataBinding.imageView)
            }
        }
    }
}