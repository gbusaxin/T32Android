package com.example.t32android.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.t32android.data.ApiFactory
import com.example.t32android.domain.pojo.QuestionAnswerItem
import com.example.t32android.domain.pojo.TrainingItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModelApp(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    var _trainingInfo = MutableLiveData<List<TrainingItem>>()
    var _answerInfo = MutableLiveData<List<QuestionAnswerItem>>()

    fun sendPost(questionItem: QuestionAnswerItem) {
        val response = ApiFactory.apiService.sendQuestion(questionItem)
    }

    fun getAnswer(id: Int) {
        val disposable = ApiFactory.apiService.getAnswer(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _answerInfo.value?.get(id)?.answer = it
                Log.d("ANSWER_CHECK", it.toString())
            }, {

            })
        compositeDisposable.add(disposable)
    }

    fun loadData(dayOfWeek: Int) {
        val disposable = when (dayOfWeek) {
            1 ->
                ApiFactory.apiService.getMonday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            2 ->
                ApiFactory.apiService.getTuesday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            3 ->
                ApiFactory.apiService.getWednesday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            4 ->
                ApiFactory.apiService.getThursday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            5 ->
                ApiFactory.apiService.getFriday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            6 ->
                ApiFactory.apiService.getSaturday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            7 ->
                ApiFactory.apiService.getSunday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })


            else -> throw Exception("NOT FOUND DAY OF WEEK")
        }
        compositeDisposable.add(disposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}