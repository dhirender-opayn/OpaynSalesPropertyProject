package com.example.opaynpropertyproject.addAdsSlides

import ServiceViewModel
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.ads_adapters.PropertyTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.adapters.ads_adapters.SellerTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.*
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.login_signup_activity.SignUpActivity
import com.example.opaynpropertyproject.map.MapExploreActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*
import kotlinx.android.synthetic.main.custom_spinner_item.view.*
import kotlinx.android.synthetic.main.fragment_add_ads1.*
import java.security.Key


class AddAdsFragment1 : BaseFragment(), ApiResponse {
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZTc0M2QxNTQwNGRhMWQ4NjU1MGI1Njk1MjBlMDJhN2U2OTdiNGFiMDY2MjgyZTgwMjc1ODJhMmZjNmEzMDQ4YTI0M2MyNTljZTUyYzAwYTkiLCJpYXQiOjE2MzA5MDY1OTQuNzI0NTY0LCJuYmYiOjE2MzA5MDY1OTQuNzI0NTY1LCJleHAiOjE2NjI0NDI1OTQuNzE5NTc1LCJzdWIiOiIyMyIsInNjb3BlcyI6W119.miTFb8YyDL186NlROqnHHjOVnCdRXEs2lhDOk_V6LLaiImsfhtQ3KBrBiPYC7hrOOXY1xLfzu1pPNCS-y21SSiB_iQ403f1i3lh_LRUSzTe5EGyNc4Ejz3Ixuekw1iuWJq_yFmvqi4j14UL_BI55y4jgtUwhV2Z3MVMgxcHmRoYKem2Tr6UhW6OiVLjzt2HExJc5JsT6SCjbSc4UXSW4IV6V-_46Q3Vv5s8iylm0vmZBF4IJso2xJPwydVd8s7jIOq1y3CPCVj6gWkHyi_SlKTQfAqKMcBDjhg1sFIGGsJP-foORlvtsR33mp2AG7Rp46R2saqH5baMEnj-W6-KFhuYQs83-Fa-nGZC8cuTH7laWD_t-7jOU_whtbHS_Ydf46gN5TSQFAOLEsTBI310qhpgTpEAf1qkinUFDEAPdJ-XuRS_iaVBFLGmwcmrsphpGpAK5_tdqTY52OSUdq63CRqJfY6UkSBdD5dWQpb9wUy-iAvb010qea1duZWqXTnVuZ0bjXRuGx_Zo4_lB8FZCLFcs0D09mlJSx_Iu_72o8vp6lH9pbCYIFMQY9s7bHeZuyyOhP1W_W4qHljT6AXO16joUrArEY3FTGvj9Sxkfzy_KVGRSxb-VerbB54bx2eHM7Zaacx3iHOWZivucINzr96BtessxXZNw1jtdkkCUBf8"


    var model: StateModel? = null
    var city_model: CityModel? = null
    var sell_property_model: SellPropertyModel? = null
    var sellType_list = listOf<SellPropertyModel.Data.Option>()
    var propertyType_list = listOf<SellPropertyModel.Data.Option>()

    var stateList = ArrayList<String>()
    var cityList = ArrayList<String>()
    var stateid: String = ""

    //sppiner
    var mainstateList = ArrayList<StateModel.Data>()
    var mainCityList = ArrayList<CityModel.Data>()
    var state_name = ""
    var city_name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ads1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sellTypeAPI(token, this)
        stateApi()


//        loaction_conatiner.setOnClickListener { // open map exlpoer
//            val intent = Intent(requireContext(),MapExploreActivity::class.java)
//            startActivity(intent)
//
//        }
        next_btn.setOnClickListener {
            checkValidation()


        }


    }

    fun stateAdapter() {
        val adapter = ArrayAdapter(
            requireActivity(),
            R.layout.custom_spinner_item, R.id.text, stateList
        )
        state_list.adapter = adapter
        state_list.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                stateid = mainstateList[position].id.toString()
                state_name = mainstateList[position].name
                serviceViewModel.getservice(
                    Keys.CITY + stateid,
                    requireContext(),
                    Keys.CITY_REQ_CODE,
                    true,
                    token,
                    true,
                    this@AddAdsFragment1
                )

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    fun cityAdapter() {
        val adapter = ArrayAdapter(
            requireActivity(),
            R.layout.custom_spinner_item, R.id.text, cityList
        )
        city_list.adapter = adapter
        city_list.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                //      city_name = mainCityList[position].name
//                subcatid = subcategorylist[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.STATE_REQ_CODE -> {
                model = gson.fromJson(response, StateModel::class.java)
                mainstateList.addAll(model!!.data)
                model!!.data.forEach {
                    stateList.add(it.name)
                }
                stateAdapter()
            }
            Keys.SELL_REQ_CODE -> {
                //check index is error or not ???
                sell_property_model = gson.fromJson(response, SellPropertyModel::class.java)

                    //sell_type_header.text = sell_property_model!!.data[0].name.toString() //To set header by api header
                    sellType_list = sell_property_model!!.data[0].options
                    recyclerView_sell_type.layoutManager =
                        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView_sell_type.adapter = SellerTypeRecyclerViewAdapter(sellType_list)


                    //proptery type
                    propertyType_list = sell_property_model!!.data[1].options
                    recyclerView_property_type.layoutManager =
                        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView_property_type.adapter =
                        PropertyTypeRecyclerViewAdapter(propertyType_list, requireContext())




            }
            Keys.CITY_REQ_CODE -> {
                cityList.clear()
                city_model = gson.fromJson(response, CityModel::class.java)
                city_model!!.data.forEach {
                    cityList.add(it.name)
                }
                cityAdapter()
            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(next_btn, errorModel.message.toString())
                Log.e("eeee", response.toString())
            }
        }
    }
//send in base fragment
//    fun sellTypeAPI() {
//        serviceViewModel.getservice(
//            Keys.SELL_TYPE_END_POINT,
//            requireContext(),
//            Keys.SELL_REQ_CODE,
//            true,
//            token,
//            true,
//            this
//        )
//    }

    fun stateApi() {
        serviceViewModel.getservice(
            Keys.STATEENDPOINT,
            requireContext(),
            Keys.STATE_REQ_CODE,
            true,
            token,
            true,
            this
        )
    }

    fun checkValidation() {
        if (manually_enter.text.toString().isNotEmpty() )
        {
            SignUpActivity.propertyFilling.address = manually_enter.text.toString()
            Log.e("test321", SignUpActivity.propertyFilling.address)
        }
         if (city_pinCode.text.toString().isNotEmpty())
        {
            SignUpActivity.propertyFilling.pinCode = city_pinCode.text.toString().toInt()
            Log.e("test123", SignUpActivity.propertyFilling.pinCode.toString())
        }
        if (SignUpActivity.propertyFilling.sell_type.length > 1 && SignUpActivity.propertyFilling.property_type.length >1 ){
            Log.e("test123", "852555555555555")
              Snackbar.make(next_btn,"Reee",Snackbar.LENGTH_LONG)

        }
    }


}