package com.example.t32android.presentation.activities

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import kotlinx.android.synthetic.main.activity_welcome.*
import java.io.File

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val anim = ValueAnimator.ofInt(0,progressBarWelcome.max)
        anim.duration = SPLASH_TIME
        anim.addUpdateListener {
            progressBarWelcome.progress = anim.animatedValue as Int
            textViewProgressCount.text = (anim.animatedValue as Int).toString()
        }
        anim.start()
        if (!ifUserExists()) {
            Handler().postDelayed({
                startActivity(Intent(this@WelcomeActivity, SignInActivity::class.java))
                finish()
            }, SPLASH_TIME)
        }else{
            Handler().postDelayed({
                startActivity(Intent(this@WelcomeActivity, MenuActivity::class.java))
                finish()
            }, SPLASH_TIME)
        }
    }

    fun ifUserExists():Boolean{
        val path: String = filesDir.absolutePath+"/"+ USER_FILE_PATH
        val file = File(path)
        return file.exists()
    }



    companion object{
        const val USER_FILE_PATH = "userData.user"
        const val SPLASH_TIME:Long = 3000
    }
}