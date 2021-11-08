package com.example.t32android.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.t32android.R

class AskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
    }

    fun OnClickFromAskAktivityBack(view: View) {
        startActivity(Intent(this,MenuActivity::class.java))
        finish()
    }
}