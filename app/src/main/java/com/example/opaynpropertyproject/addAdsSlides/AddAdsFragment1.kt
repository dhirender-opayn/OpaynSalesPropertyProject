package com.example.opaynpropertyproject.addAdsSlides

import ServiceViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.util.rangeTo
import com.example.opaynpropertyproject.MainActivity
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.ads_adapters.PropertyTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.adapters.ads_adapters.SellerTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.*
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*
import kotlinx.android.synthetic.main.custom_spinner_item.view.*
import kotlinx.android.synthetic.main.fragment_add_ads1.*
import kotlinx.android.synthetic.main.fragment_add_ads4.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.processNextEventInCurrentThread


class AddAdsFragment1 : BaseFragment(), ApiResponse {

    var model: StateModel? = null
    var city_model: CityModel? = null
    var sell_property_model: SellPropertyModel? = null
    var sellType_list = listOf<SellPropertyModel.Data.Option>()
    var propertyType_list = listOf<SellPropertyModel.Data.Option>()

    var stateList = ArrayList<String>()
    var selected_state = ""
    var selected_city = ""
    var cityList = ArrayList<String>()
    var stateid = 0
    var cityid = 0

    //sppiner
    var mainstateList = ArrayList<StateModel.Data>()
    var mainCityList = ArrayList<CityModel.Data>()
    var state_name = ""
    var city_name = ""
    var ads_model: SellPropertyModel? = null
    var bundle = Bundle()
    var addAdsRequriedActivity: AddAdsActivity? = null
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
        addAdsRequriedActivity = requireActivity() as AddAdsActivity

        if (propertyFilling.sell_type.isNotEmpty()) {
            setData()

        } else {
            startingFragement()
        }


//        loaction_conatiner.setOnClickListener { // open map exlpoer
//            val intent = Intent(requireContext(),MapExploreActivity::class.java)
//            startActivity(intent)
//
//        }


    }

    fun stateAdapter() {
        val adapter = ArrayAdapter(
            requireActivity(),
            R.layout.custom_spinner_item, R.id.text, stateList
        )
        state_spinner.adapter = adapter

        state_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {

                state_name = mainstateList[position].name
                stateid = mainstateList[position].id
                propertyFilling.state = state_name
                propertyFilling.statePosition = position
                serviceViewModel.getservice(
                    Keys.CITY + stateid,
                    requireContext(),
                    Keys.CITY_REQ_CODE,
                    true,
                    token,
                    true,
                    this@AddAdsFragment1
                )
                propertyFilling.stateID = mainstateList[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        if (!propertyFilling.stateID.equals(0)) { // check property
            state_spinner.setSelection(propertyFilling.statePosition)  // set city spinner value after PREV BUTTON Click
            // or may be there cityAdapter is called
        }

    }

    fun cityAdapter() {
        val adapter = ArrayAdapter(
            requireActivity(),
            R.layout.custom_spinner_item, R.id.text, cityList
        )
        city_spinner.adapter = adapter
        city_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                city_name = cityList[position]
                propertyFilling.city = city_name
                cityid = mainCityList[position].id
                propertyFilling.cityId = cityid
                propertyFilling.cityPosition = position

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        if (!propertyFilling.cityId.equals(0)) { // city checking
            city_spinner.setSelection(propertyFilling.cityPosition) // set city spinner value after PREV BUTTON Click

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
        if (ads_address.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(first_next_btn, getString(R.string.address_error))

        } else if (city_pinCode.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(first_next_btn, getString(R.string.pin_code_error))
        } else if (propertyFilling.sell_type.isEmpty() || propertyFilling.property_type < 0) {
            Utils.customSnakebar(first_next_btn, getString(R.string.sell_and_property_error))

        } else {

                setBasicPropertyDetails()
                propertyFilling.address = ads_address.text.toString()
                propertyFilling.pinCode = city_pinCode.text.toString()
//            ads_model = gson.fromJson(response, SellPropertyModel::class.java)




        }

    }

    fun setData() {
        val des_data = addAdsRequriedActivity!!.descriptionData
        addAdsRequriedActivity!!.your_state_progress_bar_id.setCurrentStateNumber(StateProgressBar.StateNumber.ONE)
        // set address
        ads_address.setText(propertyFilling.address)
        // set pin code
        city_pinCode.setText(propertyFilling.pinCode)

        //set value of state after prev button click
        sell_property_model = propertyFilling.sell_property_model_update
        stateList = propertyFilling.stateSpinnerList
        mainstateList.clear()
        propertyFilling.stateSpinnerModel?.let { mainstateList.addAll(it) }
        stateAdapter()


        //set value of city after prev button click
        propertyFilling.citySpinnerModel?.let { mainCityList.addAll(it) }
        city_spinner.setSelection(propertyFilling.cityPosition)
        cityAdapter()


        //Sell Recycler View
        sellType_list = propertyFilling.rv_sell_property_list!!
        recyclerView_sell_type.adapter =
            SellerTypeRecyclerViewAdapter(sellType_list)

        //Property Recycler View
        propertyType_list = propertyFilling.rv_property_type_list!!
        recyclerView_property_type.adapter =
            PropertyTypeRecyclerViewAdapter(propertyType_list, requireContext())

        //button
        first_next_btn.setOnClickListener {
            checkValidation()
        }
    }

    fun startingFragement() {
        sellTypeAPI(token, this)
        stateApi()
        first_next_btn.setOnClickListener {
            checkValidation()
        }
        ads1_parent_container.setOnClickListener {
            Utils.hideKeyboard(requireActivity())
        }

    }

    fun setBasicPropertyDetails() {
        var addPropertyHasMap = HashMap<String, Any>()


        addPropertyHasMap.put(Keys.STEP, "basic")
        addPropertyHasMap.put(Keys.LIST_TYPE, 1)
        addPropertyHasMap.put(Keys.PROPERTY_TYPE, propertyFilling.property_type)
        addPropertyHasMap.put(Keys.STATE_ID, stateid)
        addPropertyHasMap.put(Keys.CITY_ID, cityid)
        addPropertyHasMap.put(Keys.ADDRESS, ads_address.text.toString().trim())
        addPropertyHasMap.put(Keys.PINCODE, city_pinCode.text.toString().trim())
        serviceViewModel.postservice(
            Keys.ADD_PROPERTY_END_POINT,
            requireContext(),
            addPropertyHasMap,
            Keys.ADD_PROPERTY_FIRST_REQ_CODE,
            true,
            token,
            true,
            this
        )

    }
    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {

            Keys.STATE_REQ_CODE -> {
                model = gson.fromJson(response, StateModel::class.java)
                mainstateList.addAll(model!!.data)
                propertyFilling.stateSpinnerModel = mainstateList
                model!!.data.forEach {
                    stateList.add(it.name)
                    propertyFilling.stateSpinnerList.add(it.name)
                }
                stateAdapter()
            }
            Keys.SELL_REQ_CODE -> {
                //check index is error or not ???
                sell_property_model = gson.fromJson(response, SellPropertyModel::class.java)
                propertyFilling.sell_property_model_update = sell_property_model

                //sell_type_header.text = sell_property_model!!.data[0].name.toString() //To set header by api header
                sellType_list = sell_property_model!!.data[0].options
                propertyFilling.rv_sell_property_list = sellType_list
                recyclerView_sell_type.adapter =
                    SellerTypeRecyclerViewAdapter(sellType_list)



                propertyType_list = sell_property_model!!.data[1].options
                propertyFilling.rv_property_type_list = propertyType_list
                recyclerView_property_type.adapter =
                    PropertyTypeRecyclerViewAdapter(propertyType_list, requireContext())

            }
            Keys.CITY_REQ_CODE -> {
                cityList.clear()
                city_model = gson.fromJson(response, CityModel::class.java)
                mainCityList.addAll(city_model!!.data)
                propertyFilling.citySpinnerModel = mainCityList
                city_model!!.data.forEach {
                    cityList.add(it.name)

                    propertyFilling.citySpinnerList.add(it.name)
                }
                cityAdapter()
            }
            Keys.ADD_PROPERTY_FIRST_REQ_CODE -> {

                val basic_property_model = gson.fromJson(response, BasicPropertyModelSuccessfully::class.java)
                val property_id = basic_property_model.data.id
                propertyFilling.testID = property_id

               propertyFilling.propertyID = basic_property_model.data.id
                bundle.putParcelable(Keys.ADS_DATA, sell_property_model)
                addAdsRequriedActivity!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.TWO
                )
                val adsFragment2 = AddAdsFragment2()
                adsFragment2.arguments = bundle
                Utils.addReplaceFragment(
                    requireContext(),
                    adsFragment2,
                    R.id.nav_container1,
                    true,
                    false,
                    true
                )

                //Utils.customSnakebar(first_next_btn, add_property_model.message.toString())

            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(first_next_btn, errorModel.message.toString())
//                Log.e("eeee", response.toString())
            }
        }
    }


}

