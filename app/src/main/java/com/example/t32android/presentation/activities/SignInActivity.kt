package com.example.t32android.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import com.example.t32android.domain.db.UserItem
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*

class SignInActivity : AppCompatActivity() {

    companion object {
        const val USER_FILE_PATH = "userData.user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun readFileInternalStorage():UserItem? {
        try {
            val fileInputStream: FileInputStream = openFileInput(USER_FILE_PATH)
            val reader = BufferedReader(InputStreamReader(fileInputStream))
            val sb = StringBuffer()
            var line: String = reader.readLine()
            while (line != null) {
                sb.append(line)
                line = reader.readLine()
            }
            return Json.decodeFromString<UserItem>(sb.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun createUpdateFile(userData:String) {
        var json = Json.encodeToString(userData)
        val outputStream: OutputStream?
        try {
            outputStream = openFileOutput(USER_FILE_PATH, Context.MODE_PRIVATE)

            let {
                with(outputStream) {
                    write(json.toByteArray())
                    flush()
                    close()
                }
            }
            Toast.makeText(this,json,Toast.LENGTH_LONG).show()
            Log.d("WRITE_OUTPUT",json)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//        private fun saveUserDataInternalStorage(filename: String, userData: UserItem): Boolean {
//            val json = Json.encodeToString(userData)
//            return try {
//                val fos: FileOutputStream = openFileOutput("$filename.user", MODE_PRIVATE)
//                val os = ObjectOutputStream(fos)
//                os.writeObject(json)
//                os.close()
//                fos.close()
//                true
//            } catch (exception: IOException) {
//                exception.printStackTrace()
//                false
//            }
//        }

        fun onClickToMenuActivity(view: View) {
            val user = getUserDataFromXml()
            val json = Json.encodeToString(user)
            createUpdateFile(json)
            val userItem =  readFileInternalStorage()
            Toast.makeText(this,userItem.toString(),Toast.LENGTH_LONG).show()
            val intent = Intent(this@SignInActivity, MenuActivity::class.java)
            intent.putExtra(USER_FILE_PATH, json)
            startActivity(intent)
            finish()

        }

        private fun getUserDataFromXml(): UserItem {
            val name = et_distance.text.toString()
            val height = parseToDouble(et_prised.text.toString())
            val weight = parseToInt(et_weight.text.toString())
            val userItem = UserItem(name, height, weight, null)
            return userItem
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