package com.example.t32android.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionItem(

    @SerializedName("ask")
    @Expose
    val question:String? = null,
    @SerializedName("id")
    @Expose
    val id:Int? = 0,

    @Transient
    var answer:String? = null

)
