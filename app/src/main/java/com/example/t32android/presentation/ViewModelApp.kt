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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelApp(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    var _trainingInfo = MutableLiveData<List<TrainingItem>>()
    var _answerInfo = MutableLiveData<List<QuestionAnswerItem>>()

    fun sendQuestion(question: String, id: Int) {
        val response = ApiFactory.apiService.sendQuestion(question, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("QUESTION_SEND_RESPONSE", it.toString())
                Log.d("QUESTION_iSEND_RESPONSE", id.toString())
                Log.d("QUESTION_qSEND_RESPONSE", question)

            }, {
                it.printStackTrace()
            })
        compositeDisposable.add(response)
//            .enqueue(object : Callback<String?> {
//                override fun onResponse(p0: Call<String?>, p1: Response<String?>) {
//                    val resp = p1.body().toString()
//                    val cal = p0.toString()
//                    Log.d("QUESTION_SEND_RESPONSE", resp)
//                    Log.d("QUESTION_SEND_CALL", cal)
//                }
//
//                override fun onFailure(p0: Call<String?>, p1: Throwable) {
//                    p1.printStackTrace()
//                }
//            })
        Log.d("QUESTION_SEND_RESPONSE", response.toString())
    }

    fun getAnswer(id: Int) {
//        val response = ApiFactory.apiService.getAnswer(id)
//            .enqueue(object :Callback<String?>{
//                override fun onResponse(p0: Call<String?>, p1: Response<String?>) {
//                    if(p1.isSuccessful) {
//                        val answer = p1.body().toString()
//
//                        _answerInfo.value?.get(id)?.answer = answer
//                    }else{
//                        Log.d("RESPONSE_FAIL",p1.body().toString())
//                    }
//                    p1.body()?.let { Log.d("ANSWER_SEND_RESPONSE", it) }
//
//                }
//
//                override fun onFailure(p0: Call<String?>, p1: Throwable) {
//                    p1.printStackTrace()
//                }
//            })
//        Log.d("ANSWER_SEND_RESPONSE",response.toString())

        val disposable = ApiFactory.apiService.getAnswer(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _answerInfo.value?.get(id)?.answer = it
                Log.d("ANSWER_SEND_RESPONSE", it.toString())

            },
                {
                    it.printStackTrace()
                })
        compositeDisposable.add(disposable)

    }

    fun loadData(dayOfWeek: Int) {
        val disposable = when (dayOfWeek) {
            2 ->
                ApiFactory.apiService.getMonday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                        println(it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            3 ->
                ApiFactory.apiService.getTuesday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            4 ->
                ApiFactory.apiService.getWednesday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            5 ->
                ApiFactory.apiService.getThursday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            6 ->
                ApiFactory.apiService.getFriday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            7 ->
                ApiFactory.apiService.getSaturday()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _trainingInfo.value = it
                        Log.d("TRAINING_TEXT", it.toString())
                    }, {
                        Log.d("TRAINING_TEXT", it.message.toString() + "ERROR")
                    })
            1 ->
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