package br.com.charleston.github.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DataBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["url"], requireAll = false)
        fun setImageUrl(view: ImageView, url: String?) {
            url?.let {
                Glide
                    .with(view.context)
                    .load(it)
                    .apply(RequestOptions.circleCropTransform())
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["bindInt"], requireAll = false)
        fun bindInt(view: TextView, value: Int?) {
            value?.let {
                view.text = value.toString()
            }
        }
    }
}