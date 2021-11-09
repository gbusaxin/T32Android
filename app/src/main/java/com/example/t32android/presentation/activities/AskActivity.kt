package com.example.t32android.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.t32android.R
import com.example.t32android.data.ApiFactory
import com.example.t32android.domain.pojo.QuestionItem
import com.example.t32android.domain.pojo.TrainingItem
import com.example.t32android.presentation.ViewModelApp
import kotlinx.android.synthetic.main.activity_ask.*
import retrofit2.Call
import retrofit2.Response

class AskActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelApp
    var id = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]
        imageViewSendQuestion.setOnClickListener {
            val str = editTextQuestionQuestion.text.toString()
            val question = QuestionItem(str, id++, null)
            if (str != "") {
                val response = viewModel.sendQuestion(question)
                Toast.makeText(this, "${question.question} - Отправлено!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Поле вопроса пустое!", Toast.LENGTH_SHORT).show()
            }
            //
//            val call:Response<TrainingItem> = ApiFactory.apiService.sendQuestion(question)
//            call.
        }

    }

    fun OnClickFromAskAktivityBack(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}