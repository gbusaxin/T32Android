package com.example.t32android.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.t32android.R
import com.example.t32android.data.MyInternalStorage
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
    lateinit var userItem: UserItem
    val myInternalStorage:MyInternalStorage = MyInternalStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analitic)

        trainingInfoFragment = TrainingInfoFragment()

        val userString = myInternalStorage.getData(MyInternalStorage.FILE_PATH)
        userItem = Json.decodeFromString(userString)

    }

    fun onClickSetProgress(view: View) {
        val distance = et_distance.text.toString()
        val prised = et_prised.text.toString()
        userItem

        val progress = userItem?.weight?.let {
            trainingInfoFragment.currentProgressInPercent(
                it.toInt(), distance.toInt(), prised.toInt()
            )
        }
        if (progress != null) {
            userItem?.points = progress
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerViewAnalitic, trainingInfoFragment).commit()
        }

    }



}