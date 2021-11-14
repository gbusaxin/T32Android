package com.example.t32android.data

import com.example.t32android.domain.pojo.QuestionAnswerItem
import com.example.t32android.domain.pojo.TrainingItem
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("ask.php")
    fun sendQuestion(
        @Field("ask") ask:String,
        @Field("id") id:Int
    ): Call<String>

    @FormUrlEncoded
    @POST("response.php")
    fun getAnswer(@Field("id")id:Int): Call<String?>
}


