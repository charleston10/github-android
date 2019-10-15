package br.com.charleston.github.adapters

import android.widget.ImageView
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
    }
}