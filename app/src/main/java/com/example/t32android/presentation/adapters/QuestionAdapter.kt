package com.example.t32android.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t32android.R
import com.example.t32android.domain.pojo.QuestionAnswerItem
import kotlinx.android.synthetic.main.question_item.view.*

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    var list: MutableList<QuestionAnswerItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()          // redo
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.question_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = list[position]

            holder.question.text = item.question
            if (item.answer != "") {
                holder.answer.text = item.answer
            } else {
                holder.answer.text = "Тренер еще думает над ответом."
            }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val question = view.textViewQuestion
        val answer = view.textViewAnswer

    }
}