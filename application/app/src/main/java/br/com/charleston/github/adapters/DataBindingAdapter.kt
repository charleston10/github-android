package br.com.charleston.github.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DataBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["url"], requireAll = false)
        fun setImageUrl(view: ImageView, url: String) {
            Glide
                .with(view.context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        }
    }
}