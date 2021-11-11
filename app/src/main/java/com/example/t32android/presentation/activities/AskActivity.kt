package com.example.t32android.presentation.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.t32android.R
import com.example.t32android.data.PreferencesAskAns
import com.example.t32android.data.PreferencesAskAns.myList
import com.example.t32android.domain.pojo.QuestionAnswerItem
import com.example.t32android.presentation.ViewModelApp
import com.example.t32android.presentation.adapters.QuestionAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ask.*

class AskActivity : AppCompatActivity() {

    lateinit var prefAsk:SharedPreferences
    lateinit var viewModel:ViewModelApp
    lateinit var adapter:QuestionAdapter
    private lateinit var list:MutableList<QuestionAnswerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]

        loadData()
        buildRecyclerView()

        if (editTextQuestionQuestion.text.toString() != "") {
            imageViewSendQuestion.setOnClickListener {
                val textAnswer = editTextQuestionQuestion.text.toString()
                val item = QuestionAnswerItem(answer = textAnswer)
                list.add(item)
                saveData()
                for (i in 0 until list.size) {
                    list[i].id = i
                    viewModel.sendPost(list[i])
                }
            }
        }

    }

    private fun buildRecyclerView(){
        adapter = QuestionAdapter()
        adapter.list = list
        recyclerViewAsk.adapter = adapter
    }

    fun loadData(){
        prefAsk = PreferencesAskAns.mySharedPreferences(this,PreferencesAskAns.PREF_NAME)

        val gson = Gson()

        val json = prefAsk.myList
        val turnsType = object : TypeToken<List<QuestionAnswerItem>>() {}.type
        list = gson.fromJson(json,turnsType)

        for (i in 0 until list.size){
            viewModel.getAnswer(i)
            val answer = viewModel._answerInfo.value?.get(i)?.answer
            list[i].answer = answer
        }
    }

    fun saveData(){
        val gson = Gson()
        val str = gson.toJson(list)
        prefAsk.myList = str
        Log.d("CHECK_DATA",prefAsk.myList.toString())
    }

    fun OnClickFromAskAktivityBack(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
}