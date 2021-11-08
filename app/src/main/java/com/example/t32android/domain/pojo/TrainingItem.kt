package com.example.t32android.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrainingItem(
    @SerializedName("img")
    @Expose
    val image: String? = null,
    @SerializedName("text")
    @Expose
    val title: String? = null
)
