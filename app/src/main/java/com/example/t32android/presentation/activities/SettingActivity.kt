package com.example.t32android.presentation.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.t32android.R
import com.example.t32android.data.PreferenceUser
import com.example.t32android.data.PreferenceUser.deleteUser
import com.example.t32android.data.PreferenceUser.myEdit
import com.example.t32android.data.PreferenceUser.userName
import java.lang.Exception

class SettingActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        pref = PreferenceUser.mySharedPreferences(this,PreferenceUser.USER_PREF)
    }

    fun onClickDeleteUser(view: View) {
       pref.myEdit {
           it.clear()
           it.apply()
       }
        if (pref.userName == "") {
            Toast.makeText(this, "User's been deleted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        } else{
            Toast.makeText(this, "${pref.userName}", Toast.LENGTH_SHORT).show()
        }
    }

}