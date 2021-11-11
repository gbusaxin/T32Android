package com.example.t32android.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionAnswerItem(

    @SerializedName("ask")
    @Expose
    var question:String? = "",
    @Transient
    var answer:String? = "",
    @SerializedName("id")
    @Expose
    var id:Int? = 0
)
