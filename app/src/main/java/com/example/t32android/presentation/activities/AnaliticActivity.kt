package com.example.t32android.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import com.example.t32android.domain.db.UserItem
import com.example.t32android.presentation.fragments.TrainingInfoFragment
import kotlinx.android.synthetic.main.activity_analitic.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class AnaliticActivity : AppCompatActivity() {

    private lateinit var trainingInfoFragment: TrainingInfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analitic)

        trainingInfoFragment = TrainingInfoFragment()

    }

    fun onClickSetProgress(view: View) {
        val distance = et_distance.text.toString()
        val prised = et_prised.text.toString()
        val user = readFileInternalStorage()

        val progress = user?.weight?.let {
            trainingInfoFragment.currentProgressInPercent(
                it.toInt(), distance.toInt(), prised.toInt()
            )
        }
        if (progress != null) {
            trainingInfoFragment.setProgress(progress)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerViewAnalitic, trainingInfoFragment).commit()
        }

    }

    fun readFileInternalStorage(): UserItem? {
        try {
            val fileInputStream: FileInputStream = openFileInput(SignInActivity.USER_FILE_PATH)
            val reader = BufferedReader(InputStreamReader(fileInputStream))
            var line: String = reader.readLine()
            return Json.decodeFromString<UserItem>(line)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}