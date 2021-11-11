package com.example.t32android.data

import android.content.Context
import android.content.SharedPreferences

object PreferencesAskAns {

//    val ID = "ID"
//    val QUESTION = "QUESTION"
    val LIST = "LIST"
    val PREF_NAME = "PREF_NAME"

    fun mySharedPreferences(context: Context,name:String):SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.myEditQA(operation:(SharedPreferences.Editor)->Unit){
        val myEdit = edit()
        operation(myEdit)
        myEdit.apply()
    }

//    var SharedPreferences.myId
//    get() = getInt(ID,0)
//    set(value) {
//        myEditQA {
//            it.putInt(ID,value)
//        }
//    }
//
//    var SharedPreferences.myQuestion
//    get() = getString(QUESTION,"")
//    set(value) {
//        myEditQA {
//         it.putString(QUESTION,value)
//        }
//    }

    var SharedPreferences.myList
        get() = getString(LIST,"")
        set(value) {
            myEditQA {
                it.putString(LIST,value)
            }
        }






}