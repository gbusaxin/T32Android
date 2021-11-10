package com.example.t32android.domain.db

import kotlinx.serialization.Serializable


@Serializable
data class UserItem(

    val name: String? = null,


    val height: Double? = 0.0,


    val weight: Int? = 0,


    var points: Int? = 0

)