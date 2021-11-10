package com.example.t32android.data

import android.content.Context
import android.content.SharedPreferences

object PreferenceUser {

    val USER_PREF = "user_data"
    val USER_NAME = "USER_NAME"
    val USER_HEIGHT = "USER_HEIGHT"
    val USER_WEIGHT = "USER_WEIGHT"
    val USER_POINTS = "USER_POINTS"

    fun mySharedPreferences(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.myEdit(operation: (SharedPreferences.Editor) -> Unit) {
        val myEdit = edit()
        operation(myEdit)
        myEdit.commit()
    }

    var SharedPreferences.userName
    get() = getString(USER_NAME,"")
    set(value) {
        myEdit {
            it.putString(USER_NAME,value)
        }
    }

    var SharedPreferences.userHeight
    get() = getInt(USER_HEIGHT,0)
    set(value) {
        myEdit {
            it.putInt(USER_HEIGHT,value)
        }
    }

    var SharedPreferences.userWeight
        get() = getInt(USER_WEIGHT, 0)
        set(value) {
            myEdit {
                it.putInt(USER_WEIGHT, value)
            }
        }

    var SharedPreferences.deleteUser
    get() = { }
    set(value) {
        myEdit {
            it.clear()
        }
    }

    var SharedPreferences.userPoints
    get() = getInt(USER_POINTS,0)
    set(value) {
        myEdit {
            it.putInt(USER_POINTS,value)
        }
    }


}