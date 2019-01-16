package br.com.charleston.data.extensions

import android.content.Context
import androidx.annotation.RawRes
import org.apache.commons.io.IOUtils

infix fun Context.getRaw(@RawRes raw: Int): String {
    val inputStream = resources.openRawResource(raw)
    val json = IOUtils.toString(inputStream)
    IOUtils.closeQuietly(inputStream)
    return json
}