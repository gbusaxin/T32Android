package com.example.t32android.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.t32android.R
import java.lang.Exception

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }

    fun onClickDeleteUser(view: View) {
        val isDeleted = deleteFileFromInternal(USER_FILE_PATH)
        if(isDeleted){
            Toast.makeText(this,"USER WAS DELETED",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SignInActivity::class.java))
        }else{
            Toast.makeText(this,"Failed: ",Toast.LENGTH_LONG).show()
        }
    }

    fun deleteFileFromInternal(string: String):Boolean{
        return try {
            deleteFile(string)
             true
        }catch (e:Exception){
            e.printStackTrace()
            false
        }
    }

    companion object{
        const val USER_FILE_PATH = "userData.user"
    }
}