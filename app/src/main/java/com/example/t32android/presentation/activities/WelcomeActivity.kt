package com.example.t32android.presentation.activities

import android.animation.ValueAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import com.example.t32android.data.PreferenceUser
import com.example.t32android.data.PreferenceUser.userDay
import com.example.t32android.data.PreferenceUser.userName
import com.example.t32android.data.PreferenceUser.userPoints
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var pref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        pref = PreferenceUser.mySharedPreferences(this,PreferenceUser.USER_PREF)

        val anim = ValueAnimator.ofInt(0,progressBarWelcome.max)
        anim.duration = SPLASH_TIME
        anim.addUpdateListener {
            progressBarWelcome.progress = anim.animatedValue as Int
            textViewProgressCount.text = (anim.animatedValue as Int).toString()
        }
        anim.start()
        if (!ifUserExists()) {
            val calendar = Calendar.getInstance()
            val numberOfDay = (calendar.get(Calendar.DAY_OF_WEEK) - 1)
            val currentDay = pref.userDay
            if (numberOfDay != currentDay){
                pref.userPoints = 0
            }
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
        return pref.userName != ""
    }

    companion object{
        const val SPLASH_TIME:Long = 3000
    }
}