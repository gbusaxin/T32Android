package com.example.t32android.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t32android.R
import com.example.t32android.domain.pojo.QuestionItem
import kotlinx.android.synthetic.main.question_item.view.*

class QuestionAdapter:RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    var list:List<QuestionItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item,parent,false))
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = list[position]
        with(holder){
            question.text = item.question
            answer.text = item.answer
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class QuestionViewHolder(view:View):RecyclerView.ViewHolder(view){

        val question = view.textViewQuestion
        val answer = view.textViewAnswer

    }
}