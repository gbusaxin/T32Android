package com.example.t32android.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun onClickToTraining(view: View) {
        startActivity(Intent(this@MenuActivity,TrainingActivity::class.java))
    }

    fun onClickToAnalitic(view: View) {
        startActivity(Intent(this@MenuActivity,AnaliticActivity::class.java))
    }

    fun onClickToSettings(view: View) {
        startActivity(Intent(this@MenuActivity,SettingActivity::class.java))
    }

}