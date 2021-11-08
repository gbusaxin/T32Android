package com.example.t32android.presentation.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.t32android.R
import com.example.t32android.presentation.ViewModelApp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_training.*
import java.util.*

class TrainingActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        val calendar = Calendar.getInstance()
        val numberOfDay = (calendar.get(Calendar.DAY_OF_WEEK) - 1)
        Log.d("DAY_OF_WEEK_INT", numberOfDay.toString() + " :NUMBER OF DAY")
        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]
        viewModel.loadData(numberOfDay)
        viewModel._trainingInfo.observe(this,  {
            Picasso.get().load(it[0].image).into(imageViewTeamTraining)
            textViewTeamTitle.text = it[0].title
            val day = dayOfWeekIntToString(numberOfDay)
            textViewDayOfTheWeek.text = day
        })
    }

    private fun dayOfWeekIntToString(numberDayOfWeek: Int):String{
        return when(numberDayOfWeek){
            1 -> "Понедельник"
            2 -> "Вторник"
            3 -> "Среда"
            4 -> "Четверг"
            5 -> "Пятница"
            6 -> "Суббота"
            7 -> "Воскресенье"
            else -> "ERROR"
        }
    }

    fun OnClickFromTrainingToMenu(view: View) {
        finish()
    }

}