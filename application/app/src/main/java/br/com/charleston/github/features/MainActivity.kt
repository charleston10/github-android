package br.com.charleston.github.features

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import br.com.charleston.core.base.BaseActivity
import br.com.charleston.core.base.BaseViewModel
import br.com.charleston.github.R
import br.com.charleston.github.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(BaseViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}