package com.example.t32android.presentation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.t32android.R
import com.example.t32android.data.PreferencesAskAns
import com.example.t32android.data.PreferencesAskAns.myEditQA
import com.example.t32android.data.PreferencesAskAns.myList
import com.example.t32android.domain.pojo.QuestionAnswerItem
import com.example.t32android.presentation.ViewModelApp
import com.example.t32android.presentation.adapters.QuestionAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ask.*

class AskActivity : AppCompatActivity() {

    lateinit var prefAsk: SharedPreferences
    lateinit var viewModel: ViewModelApp
    lateinit var adapter: QuestionAdapter
    private lateinit var list: MutableList<QuestionAnswerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]

        loadData()

        buildRecyclerView()


        imageViewSendQuestion.setOnClickListener {
            if (editTextQuestionQuestion.text.toString() != "") {
                val textQuestion = editTextQuestionQuestion.text.toString()
                val item = QuestionAnswerItem(question = textQuestion)
                list.add(item)
                for (i in 0 until list.size) {
                    list[i].id = i
                    list[i].question?.let { it1 -> viewModel.sendQuestion(it1,i) }
                }
                adapter.list = list
                recyclerViewAsk.adapter = adapter
                saveData()
                editTextQuestionQuestion.text.clear()
            } else {
                Toast.makeText(this, "Поле вопроса пустое.", Toast.LENGTH_SHORT).show()
                clearSherPref()
                adapter.list.clear()
                adapter.notifyDataSetChanged()
            }
        }


    }

    private fun buildRecyclerView() {
        adapter = QuestionAdapter()
        adapter.list = list
        recyclerViewAsk.adapter = adapter
    }

    fun loadData() {
        prefAsk = PreferencesAskAns.mySharedPreferences(this, PreferencesAskAns.PREF_NAME)

        val questionAnswer: MutableList<QuestionAnswerItem>? =
            prefAsk.myList?.let { Gson().fromJson(it) }

        if (questionAnswer != null) {
            list = questionAnswer
        } else {
            list = mutableListOf()
        }

        for (i in 0 until list.size) {
            viewModel.getAnswer(i)
            val answer = viewModel._answerInfo.value?.get(i)?.answer
            if(answer != null) {
                list[i].answer = answer
            }else{
                list[i].answer = "Тренер еще думает над ответом."
            }
        }
    }
    fun clearSherPref() {
        prefAsk.myEditQA {
            it.clear()
            it.apply()
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun saveData() {
        val str = Gson().toJson(list)
        prefAsk.myList = str
        Log.d("CHECK_DATA", prefAsk.myList.toString())
    }

    fun OnClickFromAskAktivityBack(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}