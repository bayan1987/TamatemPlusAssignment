package com.tamatemplus.tamatemplusassignment.presenter.ui.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tamatemplus.tamatemassignment.ActivityHelper
import com.tamatemplus.tamatemplusassignment.databinding.ActivitySplashBinding
import com.tamatemplus.tamatemplusassignment.utils.StatusBarUtil

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // wait for 3 seconds to navigate to the Main Activity
        Handler().postDelayed(Runnable {
            ActivityHelper.goToActivity(this@SplashActivity,MainActivity::class.java,true)
        }, 3000)

    }


}