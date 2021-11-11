package com.example.t32android.presentation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import com.example.t32android.data.PreferenceUser
import com.example.t32android.data.PreferenceUser.userDay
import com.example.t32android.data.PreferenceUser.userHeight
import com.example.t32android.data.PreferenceUser.userName
import com.example.t32android.data.PreferenceUser.userWeight
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.io.*
import java.util.*

class SignInActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        pref = PreferenceUser.mySharedPreferences(this, PreferenceUser.USER_PREF)

    }

    fun onClickToMenuActivity(view: View) {
        val name = et_distance.text.toString()
        val height = parseToInt(et_prised.text.toString())
        val weight = parseToInt(et_weight.text.toString())

        if (name != "" && height != 0 && weight != 0) {

            val calendar = Calendar.getInstance()
            val numberOfDay = (calendar.get(Calendar.DAY_OF_WEEK) - 1)

            pref.userDay = numberOfDay
            pref.userName = name
            pref.userHeight = height
            pref.userWeight = weight

            Toast.makeText(
                this,
                "Name: ${name}, Height: ${height}, Weight: $weight",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this@SignInActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this,"Данные неккоректные",Toast.LENGTH_SHORT).show()
        }
    }

    fun parseToDouble(str: String): Double {
        return try {
            str.trim().toDouble()
        } catch (e: Exception) {
            0.0
        }
    }

    fun parseToInt(str: String): Int {
        return try {
            str.trim().toInt()
        } catch (e: Exception) {
            0
        }
    }
}