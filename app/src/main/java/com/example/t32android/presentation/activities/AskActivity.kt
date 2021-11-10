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

    var listQuestions: MutableList<QuestionItem> = mutableListOf()
    lateinit var viewModel: ViewModelApp
    var id = 0
    val myInternal = MyInternalStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]
        val adapter = QuestionAdapter()
        val json = Json.parseToJsonElement(myInternal.getData(MyInternalStorage.LIST_PATH))
        if (json != null) {
            listQuestions = Json.decodeFromJsonElement(json)
            val listId = listQuestions.get(id)?.id?.let { it }
            if (listId != null) {
                viewModel.getAnswer(listId)
                viewModel._questionLd.observe(this, {
                    adapter.list = listQuestions
                    recyclerViewAsk.adapter = adapter
                })
            }
        }
        imageViewSendQuestion.setOnClickListener {
            val str = editTextQuestionQuestion.text.toString()
            val question = QuestionItem(str, id++, null)
            listQuestions.add(question)
            val jsonList = Json.encodeToString(listQuestions)
            myInternal.saveData(jsonList, MyInternalStorage.LIST_PATH)
            if (str != "") {
                val response = viewModel.sendQuestion(question)
                Toast.makeText(this, "${question.question} - Отправлено!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "Поле вопроса пустое!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun OnClickFromAskAktivityBack(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}