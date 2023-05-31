package com.tamatemplus.tamatemplusassignment.presenter.ui.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tamatemplus.tamatemplusassignment.databinding.ActivitySplashBinding
import com.tamatemplus.tamatemplusassignment.utils.StatusBarUtil

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // These lines just for making the status bar fully transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.statusBarColor = Color.parseColor("#00000000");
            StatusBarUtil.setLightMode(this);
        }

        // wait for 3 seconds to navigate to the Main Activity
        Handler().postDelayed(Runnable {

        }, 3000)

    }


}