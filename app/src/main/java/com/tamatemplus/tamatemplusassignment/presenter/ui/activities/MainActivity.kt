package com.tamatemplus.tamatemplusassignment.presenter.ui.activities

import android.os.Bundle
import android.widget.Toast
import com.tamatemplus.tamatemplusassignment.R
import com.tamatemplus.tamatemplusassignment.databinding.ActivityMainBinding
import com.tamatemplus.tamatemplusassignment.presenter.ui.dialogs.WebViewModal
import com.tamatemplus.tamatemplusassignment.utils.NetworkUtils

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnOpenBrowser.setOnClickListener{

            if(NetworkUtils.isNetworkAvailable()){

                // showing webView browser
                val dialog = WebViewModal()
                supportFragmentManager?.let { it1 -> dialog.show(it1, "") }

            }else{
                Toast.makeText(this@MainActivity, getString(R.string.no_internet_connection),Toast.LENGTH_LONG).show()
            }
        }


    }
}