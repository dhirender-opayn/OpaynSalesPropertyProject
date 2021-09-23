package com.example.opaynpropertyproject.comman

import ServiceViewModel
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.google.gson.Gson
import kotlin.reflect.KClass

open class BaseFragment : Fragment() {
    val serviceViewModel: ServiceViewModel = ServiceViewModel()
    val gson = Gson()
    var dummytoken = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun sellTypeAPI(token: String, responseListener: ApiResponse) {
        serviceViewModel.getservice(
            Keys.SELL_TYPE_END_POINT,
            requireContext(),
            Keys.SELL_REQ_CODE,
            true,
            token,
            true,
            responseListener
        )
    }
    open fun openA(kClass: KClass<out AppCompatActivity>, bundle: Bundle? = Bundle()) {
        val intent = Intent(requireActivity(), kClass.java)
        intent.putExtras(bundle ?: Bundle())
        startActivity(intent)
    }

//    fun openFragemtn(){
//        val ft = fragmentManager!!.beginTransaction()
//        ft.replace(R.id.Two, SecondFragment(), "NewFragmentTag")
//        ft.commit()
//    }




}