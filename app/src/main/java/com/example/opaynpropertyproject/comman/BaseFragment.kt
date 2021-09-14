package com.example.opaynpropertyproject.comman

import ServiceViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.google.gson.Gson

open class BaseFragment : Fragment() {
    val serviceViewModel: ServiceViewModel = ServiceViewModel()
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMGNlMmI5MDY3NGViNjhiNjY3MWM1NTk1NzhkNmIxZTEyNWFkYjU0OWE1OTQ0ZjE4YjI3ZTEyNmEzZjU3MWM1ZWYzOWJkNjkyOTkyNTU4MDMiLCJpYXQiOjE2MzE1Mjg4MjYuODAwODEzLCJuYmYiOjE2MzE1Mjg4MjYuODAwODE2LCJleHAiOjE2NjMwNjQ4MjYuNzk0ODYsInN1YiI6IjIzIiwic2NvcGVzIjpbXX0.jpy6jl-pIDtYhCAv12W1WrFbbi336jZOiv2VkJFomr5gy0JKOs-dpYREMJhdf1MPWRKVzXPnLh9Sqzhw0rnfQ40zS0lblTOV6vRplVYT_uKuNK7ZKoa4otRp2BIOVF7qPfCwLxDpWdtJa0nCLa6L8Av5rBw1qOzl46toPjNiUS_UdW6IAOtze1DzVrS8jpbZJepHUQfdIfI6NFbaYSmcRHE9v0N8WWtExMIPaH1tP3PbUaM74l9PXsR58UU1jk_PWOpivjhv64gtxxnJRxRzBDtQD2DRmwRco07bQehiu68rlsv7Tu3Q9Hfl12DADyuRfUPYWQLxxs8eVODqNe2nDqkH_VbExCbDkv3ifdre-yp3sjDUbJ0iCIPOX5P9fl0NsicPxa-lKfAZCl8UEsrPfdoHuI4lVvGbor9iANaqMmTAzWw6vIAzz_eD6pAIpuSBECJ6Uyt1LyE7c_rn7rs4s_aXknqTwkDL95Xx1zV7IPchzTxUepyCN0xxYXdtx81CVCORj8qp22DO85DyuFtvtuhsjzDBeZC9dhGa-dcU0NHxLZKNFMDs76opR2arcy8GVaK_SeE73_xu7ygpR3UGxr83UpePWSs45qMefqXBAesxQFFrN38Ia7M7g3RCYVemd1tnkiZ7_Okz8S1Bf5NCd25DeawvTGJCKn862XlEHZ8"



    val gson = Gson()


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

//    fun openFragemtn(){
//        val ft = fragmentManager!!.beginTransaction()
//        ft.replace(R.id.Two, SecondFragment(), "NewFragmentTag")
//        ft.commit()
//    }




}