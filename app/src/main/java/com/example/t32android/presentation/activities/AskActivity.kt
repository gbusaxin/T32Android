package com.example.t32android.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.t32android.R
import com.example.t32android.data.MyInternalStorage
import com.example.t32android.domain.pojo.QuestionItem
import com.example.t32android.presentation.ViewModelApp
import com.example.t32android.presentation.adapters.QuestionAdapter
import kotlinx.android.synthetic.main.activity_ask.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class AskActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)



    }

    fun OnClickFromAskAktivityBack(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}