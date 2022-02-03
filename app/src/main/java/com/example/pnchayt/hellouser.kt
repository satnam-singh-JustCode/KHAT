package com.example.pnchayt

import android.animation.Animator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.example.pnchayt.databinding.ActivityHellouserBinding
import com.example.pnchayt.databinding.ActivitySplashscreenBinding

class hellouser : AppCompatActivity() {
    private lateinit var binding : ActivityHellouserBinding

    override fun onStart() {
        super.onStart()
        hideStatusBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHellouserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email = intent.getStringExtra("name")


//        Handler().postDelayed({
            binding.animation.playAnimation()

            binding.animation.addAnimatorListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {

                    binding.namemail.text = "Logging in as:-"+" "+email
                    Handler().postDelayed({
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }, 1700)

                }
                override fun onAnimationEnd(animation: Animator?) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

            })
//        }, 8)


    }


    fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= 16) {
            getWindow().setFlags(
                AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT,
                AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT
            );
            getWindow().getDecorView().setSystemUiVisibility(3328);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
    }

}