package com.example.t32android.presentation.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import com.example.t32android.data.PreferenceUser
import com.example.t32android.data.PreferenceUser.userPoints
import com.example.t32android.data.PreferenceUser.userWeight
import kotlinx.android.synthetic.main.activity_analitic.*

class AnaliticActivity : AppCompatActivity() {

    lateinit var pref :SharedPreferences
    val TARGET_POINT = 25000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analitic)

        pref = PreferenceUser.mySharedPreferences(this,PreferenceUser.USER_PREF)

        progressBarTraining.progress = progressInPercent(pref.userPoints)


        buttonEnterTraining.setOnClickListener {
            val distance = et_distance.text.toString().trim().toInt()
            val prised = et_prised.text.toString().trim().toInt()
            val weight = pref.userWeight

            val progress = calculateProgress(distance,prised,weight)
            pref.userPoints += progress
            progressBarTraining.progress = progressInPercent(pref.userPoints)
            Log.d("ANALITIC_LOG",pref.userPoints.toString())
            Log.d("ANALITIC_LOG",progressBarTraining.progress.toString())
            textViewProgressTraining.text = "Прогресс: ${pref.userPoints}"

        }

    }

    fun calculateProgress(distance:Int,prised:Int,weight:Int):Int{
        return ((((distance * 1000) + (prised * 10)) * weight) / 10)
    }

    fun progressInPercent(progress:Int):Int{
        return (progress / TARGET_POINT) * 100
    }



}