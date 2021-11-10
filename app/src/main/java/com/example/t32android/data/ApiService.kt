package com.example.t32android.data

import com.example.t32android.domain.pojo.QuestionItem
import com.example.t32android.domain.pojo.TrainingItem
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("monday.json")
    fun getMonday(): Single<List<TrainingItem>>

    @GET("tuesday.json")
    fun getTuesday(): Single<List<TrainingItem>>

    @GET("wednesday.json")
    fun getWednesday(): Single<List<TrainingItem>>

    @GET("thursday.json")
    fun getThursday(): Single<List<TrainingItem>>

    @GET("friday.json")
    fun getFriday(): Single<List<TrainingItem>>

    @GET("saturday.json")
    fun getSaturday(): Single<List<TrainingItem>>

    @GET("monday.json")
    fun getSunday(): Single<List<TrainingItem>>

    @POST("ask.php")
    fun sendQuestion(@Body question: QuestionItem): Call<TrainingItem>

    @GET("response.php")
    fun getAnswer(@Body id: Int): Single<String>
}


