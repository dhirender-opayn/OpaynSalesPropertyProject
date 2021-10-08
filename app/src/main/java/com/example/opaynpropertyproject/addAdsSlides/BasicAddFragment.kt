package com.example.opaynpropertyproject.addAdsSlides

import android.os.Bundle
import android.provider.CallLog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.property_setup_adapters.PropertyTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.adapter.property_setup_adapters.SellerTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.*
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_seller_buyer_profile_set.*
import kotlinx.android.synthetic.main.basic_add_fragment.*
import kotlinx.android.synthetic.main.custom_spinner_item.view.*
import kotlinx.android.synthetic.main.toolbar.*


class BasicAddFragment : BaseFragment(), ApiResponse, View.OnClickListener {

    var model: StateModel? = null
    var city_model: CityModel? = null
    var sell_property_model: SellPropertyModel? = null
    var sellType_list = listOf<SellPropertyModel.Data.Option>()
    var propertyType_list = listOf<SellPropertyModel.Data.Option>()

    var stateList = ArrayList<String>()
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
    var dealerAddRequriedActivity: DealerAddActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.basic_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dealerAddRequriedActivity = requireActivity() as DealerAddActivity

        if (propertyFilling.sell_type.isNotEmpty()) {
            setData()
        } else {
            startingFragement()
        }
        setclick()

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

    private fun setclick() {

        do_later_btn.setOnClickListener(this)
        first_next_btn.setOnClickListener(this)
        header_filer.setOnClickListener(this)
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
                if (mainstateList.size > 0) {
                    state_name = mainstateList[position].name

                    propertyFilling.state = state_name
                    propertyFilling.statePosition = position
                    stateid = mainstateList[position].id
                    serviceViewModel.getservice(
                        Keys.CITY + stateid, requireContext(), Keys.CITY_REQ_CODE, true, token,
                        true, this@BasicAddFragment
                    )
                    propertyFilling.stateID = mainstateList[position].id
                }

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

    fun cityAdapter(position: Int) {
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
        city_spinner.setSelection(propertyFilling.cityPosition) // set city spinner value after PREV BUTTON Click


    }


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
            propertyFilling.sell_property_model_update = sell_property_model
//            ads_model = gson.fromJson(response, SellPropertyModel::class.java)

//            val adsFragment2 = ProfileAddFragment()
//            bundle.putParcelable(Keys.ADS_DATA, sell_property_model)
//            adsFragment2.arguments = bundle
//            Utils.addReplaceFragment(
//                requireContext(),
//                adsFragment2,
//                R.id.nav_container1,
//                true,
//                false,
//                true
//            )


        }

    }


    fun setEdit() {
        for (item in propertyFilling.fullPropertySetUpModel!!.indices) {
            if (propertyFilling.edit_id == propertyFilling.fullPropertySetUpModel!![item].id) {

                val des_data = dealerAddRequriedActivity!!.descriptionData
                dealerAddRequriedActivity!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.ONE
                )

                // set address
                ads_address.setText(propertyFilling.fullPropertySetUpModel!![item].address.toString())
                // set pin code
                city_pinCode.setText(propertyFilling.fullPropertySetUpModel!![item].pincode.toString())
                propertyFilling.fullPropertySetUpModel!![item].profile


//                //set value of state after prev button click
//                stateList = propertyFilling.stateSpinnerList
//                 propertyFilling.stateSpinnerModel?.let { mainstateList.addAll(it) }


                if (propertyFilling.editpost != null && propertyFilling.editpost?.state != null) {

                    for (i in mainstateList.indices) {
                        if (mainstateList[i].id.equals(propertyFilling.editpost!!.state)) {

                            state_spinner.setSelection(i)
                        }


                    }

                }


            }
        }

    }

    fun propertydetailapi(token: String, responseListener: ApiResponse) {
        serviceViewModel.getservice(
            Keys.DEALERPEOPERTYDETAIL + propertyFilling.edit_id,
            requireContext(),
            Keys.REQ_DEALERPEOPERTYDETAIL,
            true,
            HomeActivity.token,
            false,
            this
        )
    }

    fun setData() {
        val des_data = dealerAddRequriedActivity!!.descriptionData
        dealerAddRequriedActivity!!.your_state_progress_bar_id.setCurrentStateNumber(
            StateProgressBar.StateNumber.ONE
        )

        sell_property_model = propertyFilling.sell_property_model_update

        ads_address.setText(propertyFilling.address)
        city_pinCode.setText(propertyFilling.pinCode)
        mainstateList = propertyFilling.stateSpinnerModel

        stateList = propertyFilling.stateSpinnerList
        //set value of state after prev button click


        selltypeAdapter()
        propertyTypeAdapter()

        stateAdapter()


        // when   click on profile fragment previous buttonbutton
        if (propertyFilling.edit_flag) {
            startingFragement()
        }


    }

    fun selltypeAdapter() {
        if (propertyFilling.sell_type.isNotEmpty()) {
            sellType_list = propertyFilling.rv_sell_property_list!!
        }
        recyclerView_sell_type.adapter = SellerTypeRecyclerViewAdapter(sellType_list)
    }

    fun propertyTypeAdapter() {
        if (propertyFilling.sell_type.isNotEmpty()) {
            propertyType_list = propertyFilling.rv_property_type_list!!
        }
        recyclerView_property_type.adapter =
            PropertyTypeRecyclerViewAdapter(propertyType_list)
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
        addPropertyHasMap.put(Keys.LIST_TYPE, propertyFilling.sell_type)
        addPropertyHasMap.put(Keys.PROPERTY_TYPE, propertyFilling.property_type)
        addPropertyHasMap.put(Keys.STATE_ID, stateid)
        addPropertyHasMap.put(Keys.CITY_ID, cityid)
        addPropertyHasMap.put(Keys.ADDRESS, ads_address.text.toString().trim())
        addPropertyHasMap.put(Keys.PINCODE, city_pinCode.text.toString().trim())
        if (propertyFilling.edit_flag) {
            addPropertyHasMap.put(Keys.PROPERTY_ID, propertyFilling.editpost?.id.toString())
        }

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
                if (propertyFilling.edit_flag) {
                    setEdit()
                    propertydetailapi(token, this)
                }

            }
            Keys.REQ_DEALERPEOPERTYDETAIL -> {
                val model = gson.fromJson(response, PropertyDetailModel::class.java)
                propertyFilling.detailModel = model
            }
            Keys.SELL_REQ_CODE -> {

                //check index is error or not ???
                sell_property_model = gson.fromJson(response, SellPropertyModel::class.java)

                propertyFilling.sell_property_model_update = sell_property_model

                //sell_type_header.text = sell_property_model!!.data[0].name.toString() //To set header by api header
                sellType_list = sell_property_model!!.data[0].options
                propertyFilling.rv_sell_property_list = sellType_list

                propertyType_list = sell_property_model!!.data[1].options
                propertyFilling.rv_property_type_list = propertyType_list

                /* edit property*/
                if (propertyFilling.edit_flag) {
                    for (data in sellType_list.indices) {
                        if (sellType_list[data].id.toString() == propertyFilling.editpost!!.list_type) {
                            sellType_list[data].flag = true
                        } else {
                            sellType_list[data].flag = false
                        }
                    }
                    for (data in propertyType_list.indices) {
                        if (propertyType_list[data].id.toString() == propertyFilling.editpost!!.property_type) {
                            propertyType_list[data].flag = true
                        } else {
                            propertyType_list[data].flag = false
                        }
                    }
                }

                selltypeAdapter()
                propertyTypeAdapter()

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
                var citypos = 0
                if (propertyFilling.edit_flag) {
                    for (i in mainCityList.indices) {
                        if (mainCityList[i].id.equals(propertyFilling.editpost!!.city)) {

                            citypos = i
                        }


                    }
                }
                cityAdapter(citypos)
            }
            Keys.ADD_PROPERTY_FIRST_REQ_CODE -> {

                val basic_property_model =
                    gson.fromJson(response, BasicPropertyModelSuccessfully::class.java)
                val property_id = basic_property_model.data.id
                val user_id = basic_property_model.data.user_id
                propertyFilling.propertyID = property_id
                propertyFilling.userID = user_id
                propertyFilling.propertyID = basic_property_model.data.id

                dealerAddRequriedActivity!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.TWO
                )
                val adsFragment2 = ProfileAddFragment()
                bundle.putParcelable(Keys.ADS_DATA, sell_property_model)
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
//                Utils.customSnakebar(first_next_btn, errorModel.message.toString())
//                Log.e("eeee", response.toString())
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.do_later_btn -> {
                requireActivity().finish()
                propertyFilling.edit_flag = false
            }
            R.id.first_next_btn -> {
                checkValidation()
            }
        }

    }


}

