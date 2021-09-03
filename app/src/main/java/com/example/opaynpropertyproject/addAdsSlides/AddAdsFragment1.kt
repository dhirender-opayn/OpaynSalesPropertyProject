package com.example.opaynpropertyproject.addAdsSlides

import ServiceViewModel
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
import com.example.opaynpropertyproject.api_model.CityModel
import com.example.opaynpropertyproject.api_model.StateModel
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*
import kotlinx.android.synthetic.main.custom_spinner_item.view.*
import kotlinx.android.synthetic.main.fragment_add_ads1.*
import java.security.Key


class AddAdsFragment1 : BaseFragment(), ApiResponse {

    lateinit var serviceViewModel: ServiceViewModel
    var model: StateModel? = null
    var city_model: CityModel? = null
    var sell_property_model: SellPropertyModel? = null
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZDg3Y2I4MzlhNjdkYjc5NWQ3YTNhOGJjNjIxOWUyYTEzNDU1YWNmMjJhNDhjZWUxZTdlOWNkYzAxYzU5YmYwNTU1NDQwNTc5ZDQzYWJhY2UiLCJpYXQiOjE2MzA2NTQ2MzguOTk4MjU0LCJuYmYiOjE2MzA2NTQ2MzguOTk4MjU3LCJleHAiOjE2NjIxOTA2MzguOTI3MjEsInN1YiI6IjIyIiwic2NvcGVzIjpbXX0.CY3R4dTuj3UT5b-DeEPBeypYEXwrMQdLYhGMPpXjIdn_EHJvVhOczqv3qMH9bF_ipTzqbFQ0qj8FC97a4dhJD4QEYQrnIxFICB4JTaqjJv5Egw6-NqunNQx7Knc_tyAwWxEZifwsMqhNY-FEPheg3LAdy-eRIHfzf7eiTseEAYF5GB976eW-fnVFDX2IEW8Anq3rjtAFrTRy3e7n9-3eq9Imnw71Rzp8q868WcunhuEcGwcZA1HnKKdZswlqMcvxTq_FmHTKyqFDd4tD2qfXKgaO_XyktyHzX5ZMd5NV-IWfVqhBsxEWSzqHjR0DNKLt3KtsQ1KFxOMlVqP7OuC7rtGI_16ypOG-72n8DyyVoao40XW20z6m7Ix6w0lLQ6-rD_oqUW3anLObDMwdAyQ2XU3vTFCGpRvqkk9heMiwWpMyDzFyoLntHq3b6M-oDFnQojhaY5exbpsYiGtzYRd4H8OiXQUmnBmcuQb_43F73Xqo8Yuj5Ksyfhiq1ofcJMD-trGxTJ0NLyGY5R3l9FlNCiXCbcW6wELYyig6KkeZ8Q0V1jAHqMibESPHwo-v34q4YApw7zpgdzGVmGcZHQIUiqp6vuZUeOt24xNLgWO3i9U4DFMLOPw_D-Uhx_dZrOHJy71W-ZZa3hPTnwbA4tPTQ-lXSQDwSH2-hUqP-lba030"
    var sellType_list = listOf<SellPropertyModel.Data.Option>()
    var stateList = ArrayList<String>()
    var mianstateList = ArrayList<StateModel.Data>()
    var cityList = ArrayList<String>()
    var stateid: String = ""
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
        //state api
        serviceViewModel = ServiceViewModel()


        sellTypeAPI()
        stateApi()



        recyclerView_property_type.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView_property_type.adapter = PropertyTypeRecyclerViewAdapter(stateList)


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
                stateid = mianstateList[position].id.toString()
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
                mianstateList.addAll(model!!.data)
                model!!.data.forEach {
                    stateList.add(it.name)
                }
                stateAdapter()
            }
            Keys.SELL_REQ_CODE -> {
                sell_property_model = gson.fromJson(response, SellPropertyModel::class.java)
                sell_property_model!!.data.forEach {
                    if (it.id == Keys.SELL_ID) {

                        sellType_list = it.options
//                        sellType_list.add(it.options)
                    }
                }

                recyclerView_sell_type.layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                recyclerView_sell_type.adapter = SellerTypeRecyclerViewAdapter(sellType_list)
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

    fun sellTypeAPI() {
        serviceViewModel.getservice(
            Keys.SELL_TYPE_END_POINT,
            requireContext(),
            Keys.SELL_REQ_CODE,
            true,
            token,
            true,
            this
        )
    }
    fun stateApi(){
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


}