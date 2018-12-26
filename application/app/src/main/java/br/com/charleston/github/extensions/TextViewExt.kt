package br.com.charleston.github.extensions

import android.os.Handler
import android.widget.TextView

fun TextView.typeWriter(text: String, delay: Long = 10) {
    var index = 0
    val handler = Handler()

    val characterAdder = object : Runnable {
        override fun run() {
            this@typeWriter.text = text.subSequence(0, index++)
            if (index <= text.length) {
                handler.postDelayed(this, delay)
            }
        }
    }
    handler.removeCallbacks(characterAdder)
    handler.postDelayed(characterAdder, delay)
}