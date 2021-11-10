package com.example.t32android.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import kotlinx.android.synthetic.main.activity_analitic.*

class AnaliticActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analitic)

    }

    fun onClickSetProgress(view: View) {
        val distance = et_distance.text.toString()
        val prised = et_prised.text.toString()
    }

}