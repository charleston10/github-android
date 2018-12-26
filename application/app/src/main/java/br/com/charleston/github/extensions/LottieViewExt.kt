package br.com.charleston.github.extensions

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat
import br.com.charleston.github.R
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback

fun LottieAnimationView.disable() {
    this.isEnabled = false
    this.addValueCallback(
        KeyPath("**"),
        LottieProperty.COLOR_FILTER,
        LottieValueCallback<ColorFilter>(
            PorterDuffColorFilter(
                ContextCompat.getColor(this.context, R.color.grey_100),
                PorterDuff.Mode.LIGHTEN
            )
        )
    )
}

fun LottieAnimationView.enable() {
    this.isEnabled = true
    this.addValueCallback(
        KeyPath("**"),
        LottieProperty.COLOR_FILTER,
        LottieValueCallback<ColorFilter>(null)
    )
}

