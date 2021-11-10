package com.example.t32android.data

import android.content.Context
import android.widget.Toast
import com.example.t32android.domain.db.UserItem
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception

class MyInternalStorage(val context:Context?) {

    fun saveData(userData: String,type:String):Boolean {
        val output = context?.openFileOutput(type, Context.MODE_PRIVATE)
        try {
            output?.write(userData.toByteArray())
            output?.flush()
            output?.close()
            Toast.makeText(context, "$userData is saved", Toast.LENGTH_LONG).show()
            return true
        } catch (e:Exception){
            e.printStackTrace()
            Toast.makeText(context, "$userData is not saved: ${e.printStackTrace()}", Toast.LENGTH_LONG).show()
            return false
        }
    }

    fun getData(type:String):String{
        val input = context?.openFileInput(type)
        val reader = BufferedReader(InputStreamReader(input))
        val builder = StringBuilder()
        var line:String? = null
        line = reader.readLine()
        while (line != null){
            builder.append(line)
            line = reader.readLine()
        }
        return line.toString()
    }

    companion object {
        const val LIST_PATH = "userlist.txt"
        const val FILE_PATH = "user.txt"
    }

}