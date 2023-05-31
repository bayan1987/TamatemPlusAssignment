package com.tamatemplus.tamatemplusassignment.presenter.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tamatemplus.tamatemplusassignment.R
import com.tamatemplus.tamatemplusassignment.databinding.ActivityMainBinding
import com.tamatemplus.tamatemplusassignment.utils.NetworkUtils

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnOpenBrowser.setOnClickListener{

            if(NetworkUtils.isNetworkAvailable()){

            }else{
                Toast.makeText(this@MainActivity, getString(R.string.no_internet_connection),Toast.LENGTH_LONG).show()
            }
        }


    }
}