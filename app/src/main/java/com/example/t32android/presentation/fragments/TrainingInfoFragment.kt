package com.example.t32android.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.t32android.R
import kotlinx.android.synthetic.main.fragment_training_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TrainingInfoFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewProgressTraining
        progressBarTraining
    }

    fun setProgress(int:Int){
        progressBarTraining.setProgress(int)
    }

    fun currentProgressInPercent(userWeight:Int,distance: Int,prised: Int):Int{
        val currentNumber = makeProgress(distance,prised,userWeight)
        return  (currentNumber.div(1000) ?: 1) * 100
    }

    private fun makeProgress(distance: Int, prised: Int, weight: Int): Int {
        return if (distance != 0 && prised != 0) {
            ((((distance * 1000) + (prised) * 10) * weight) / 10)
        } else {
            0
        }
    }
    companion object{
        const val TARGET_POINT = 25000
    }

//    fun readFileInternalStorage(view:View): UserItem? {
//        try {
//            val fileInputStream: FileInputStream? =
//                context?.openFileInput(SignInActivity.USER_FILE_PATH) ?: null
//            val reader = BufferedReader(InputStreamReader(fileInputStream))
//            val sb = StringBuffer()
//            var line: String = reader.readLine()
//            while (line != null) {
//                sb.append(line)
//                line = reader.readLine()
//            }
//            return Json.decodeFromString<UserItem>(sb.toString())
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }


}