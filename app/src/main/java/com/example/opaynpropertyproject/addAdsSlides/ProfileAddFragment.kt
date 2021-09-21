package com.example.opaynpropertyproject.addAdsSlides

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.profile_add_fragment.*

import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.adapter.property_setup_adapters.*
import com.example.opaynpropertyproject.api_model.PropertyProfileModelSuccessfully
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token

import com.google.android.material.slider.Slider


class ProfileAddFragment : BaseFragment(), View.OnClickListener, ApiResponse {

    var bundle = Bundle()
    var sellPropertyBundle: SellPropertyModel? = null
    var sell_list = listOf<SellPropertyModel.Data.Option>()
    var area_list = listOf<SellPropertyModel.Data.Option>()
    var postedby_list = listOf<SellPropertyModel.Data.Option>()
    var amenities_list = listOf<SellPropertyModel.Data.Option>()
    var age_of_property_list = listOf<SellPropertyModel.Data.Option>()
    var entranceList = listOf<SellPropertyModel.Data.Option>()
    var furnishList = listOf<SellPropertyModel.Data.Option>()
    var dealerAddRequiredActivity2: DealerAddActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dealerAddRequiredActivity2 = requireActivity() as DealerAddActivity
        setClick()
        sqftSlider()
        bedSlider()
        bathroomSlider()
        dealerAddRequiredActivity2!!.your_state_progress_bar_id.setCurrentStateNumber(
            StateProgressBar.StateNumber.TWO
        )

        if (propertyFilling.edit_flag) {
            editProfile()
        } else if (propertyFilling.no_bed != 0.0f) { // if user press back button
            setDataAdsFragement2()

        } else {
            startingFragment2()
        }

    }


    //button click //
    private fun setClick() {
        pre_btn.setOnClickListener(this)
        next_btn_ads2.setOnClickListener(this)
    }

    // Edit property //
    fun editProfile() {

        propertyFilling.AdsAdd2Bundle = bundle
        bundle = requireArguments()
        sellPropertyBundle = bundle.getParcelable<SellPropertyModel>(Keys.ADS_DATA)


        for (item in propertyFilling.fullPropertySetUpModel!!.indices) {
            if (propertyFilling.edit_id == propertyFilling.fullPropertySetUpModel!![item].id) {


                if (propertyFilling.fullPropertySetUpModel!![item].profile.bath_rooms != null) {
                    ads_bathroom.value =
                        propertyFilling.fullPropertySetUpModel!![item].profile.bath_rooms.toString()
                            .toFloat()
                    show_bathroom.text =
                        propertyFilling.fullPropertySetUpModel!![item].profile.bath_rooms.toString() + "-Bathroom"
                }
                if (propertyFilling.fullPropertySetUpModel!![item].profile.bed_rooms != null) {
                    ads_beds.value =
                        propertyFilling.fullPropertySetUpModel!![item].profile.bed_rooms.toFloat()
                    show_bed.text =
                        propertyFilling.fullPropertySetUpModel!![item].profile.bed_rooms.toString() + "-Bed"

                }
                if (propertyFilling.fullPropertySetUpModel!![item].profile.area != null) {

                    sqft_input.value =
                        propertyFilling.fullPropertySetUpModel!![item].profile.area.toFloat()
                    show_sqft.text =
                        propertyFilling.fullPropertySetUpModel!![item].profile.area.toString() + "-sqft"

                }

                HitAdsApi()


            }
        }


    }

    fun HitAdsApi() {
        //possession types
        sell_list = sellPropertyBundle!!.data[1].options
        possessionAdapter()
        propertyFilling.sell_list_rev = sell_list

        area_list = sellPropertyBundle!!.data[2].options
        areaAdapter()
        propertyFilling.area_list_rev = area_list


        //posted By
        postedby_list = sellPropertyBundle!!.data[3].options
        postbyAdapter()
        propertyFilling.postedby_list_rev = postedby_list

        //Amenities
        amenities_list = sellPropertyBundle!!.data[7].options
        ammentiesAdapter()
        propertyFilling.amenities_list_rv = amenities_list

        //age of property
        age_of_property_list = sellPropertyBundle!!.data[4].options
        ageofPropertyAdapter()
        propertyFilling.age_of_property_rv = age_of_property_list

        //Entrance
        entranceList = sellPropertyBundle!!.data[5].options
        entranceAdapter()
        propertyFilling.entranceList_rev = entranceList

        //Furnishing
        furnishList = sellPropertyBundle!!.data[6].options
        furnishAdapter()
        propertyFilling.furnishList_rev = furnishList

    }

    fun setDataAdsFragement2() {
        dealerAddRequiredActivity2!!.your_state_progress_bar_id.setCurrentStateNumber(
            StateProgressBar.StateNumber.TWO
        )

        //sqft area
        sqft_input.value = propertyFilling.sqft
        show_sqft.text = propertyFilling.sqft.toString() + "-sqft"


        ads_beds.value = propertyFilling.no_bed
        show_bed.text = propertyFilling.no_bed.toInt().toString() + "-Bed"

        ads_bathroom.value = propertyFilling.no_bathroom
        show_bathroom.text = propertyFilling.no_bathroom.toInt().toString() + "-Bathroom"

        //sell list - Poessession
        sell_list = propertyFilling.sell_list_rev!!
        possessionAdapter()
        //area list
        area_list = propertyFilling.area_list_rev!!
        areaAdapter()
        //postedby list
        postedby_list = propertyFilling.postedby_list_rev!!
        postbyAdapter()
        //Amenities
        amenities_list = propertyFilling.amenities_list_rv!!
        rv_amenities.adapter = AmenitiesRecyclerAdapter(amenities_list, requireContext())

        //age of property
        age_of_property_list = propertyFilling.age_of_property_rv!!
        rv_age_of_property.adapter = AgeOfPropertyAdapter(age_of_property_list, requireContext())

        // Entrance
        entranceList = propertyFilling.entranceList_rev!!
        rv_entrance.adapter = EntranceRecyclerAdapter(entranceList, requireContext())

        //Furnishing
        furnishList = propertyFilling.furnishList_rev!!
        rv_furnishing.adapter = FurnishRecyclerAdapter(furnishList, requireContext())
    }


    fun possessionAdapter() {
        if (propertyFilling.edit_flag) {
            for (i in sell_list.indices) {
                if (sell_list[i].id.equals(propertyFilling.editpost?.profile?.possession)) {
                    sell_list[i].flag = true
                } else {
                    sell_list[i].flag = false
                }
            }
        }

        rv_possession_list.adapter = PossessionStatusRecyclerAdaper(sell_list, requireContext())
    }

    private fun postbyAdapter() {
        if (propertyFilling.edit_flag) {
            for (i in postedby_list.indices) {
                if (postedby_list[i].id.equals(propertyFilling.editpost?.profile?.posted_by)) {
                    postedby_list[i].flag = true
                } else {
                    postedby_list[i].flag = false
                }
            }
        }
        rv_posted_by.adapter = PostedByRecyclerAdapter(postedby_list, requireContext())

    }

    private fun ammentiesAdapter() {
        if (propertyFilling.edit_flag) {
            for (i in amenities_list.indices) {
                for (j in propertyFilling.detailModel!!.data[0].amenities.indices) {
                    if (propertyFilling.detailModel!!.data[0].amenities[j].equals(amenities_list[i].id)) {
                        amenities_list[i].flag = true
                    }
                }
            }
        }
        rv_amenities.adapter = AmenitiesRecyclerAdapter(amenities_list, requireContext())

    }


    fun areaAdapter() {

        if (propertyFilling.edit_flag) {
            for (i in area_list.indices) {
                if (area_list[i].id.equals(propertyFilling.editpost?.profile?.area_type)) {
                    area_list[i].flag = true
                } else {
                    area_list[i].flag = false
                }
            }
        }
        rv_area.adapter = AreaRecyclerViewAdapter(area_list, requireContext())
    }

    fun entranceAdapter() {

        if (propertyFilling.edit_flag) {
            for (i in entranceList.indices) {
                if (entranceList[i].id.equals(propertyFilling.editpost?.profile?.entrance)) {
                    entranceList[i].flag = true
                } else {
                    entranceList[i].flag = false
                }
            }
        }
        rv_entrance.adapter = EntranceRecyclerAdapter(entranceList, requireContext())
    }

    fun furnishAdapter() {

        if (propertyFilling.edit_flag) {
            for (i in furnishList.indices) {
                if (furnishList[i].id.equals(propertyFilling.editpost?.profile?.furnishing)) {
                    furnishList[i].flag = true
                } else {
                    furnishList[i].flag = false
                }
            }
        }
        rv_furnishing.adapter = FurnishRecyclerAdapter(furnishList, requireContext())
    }

    private fun ageofPropertyAdapter() {
        if (propertyFilling.edit_flag) {
            for (i in age_of_property_list.indices) {
                if (age_of_property_list[i].id.equals(propertyFilling.editpost?.profile?.age)) {
                    age_of_property_list[i].flag = true
                } else {
                    age_of_property_list[i].flag = false
                }
            }
        }
        rv_age_of_property.adapter = AgeOfPropertyAdapter(age_of_property_list, requireContext())

    }


    fun startingFragment2() {
        propertyFilling.AdsAdd2Bundle = bundle
        bundle = requireArguments()

        sellPropertyBundle = bundle.getParcelable<SellPropertyModel>(Keys.ADS_DATA)

        HitAdsApi()
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pre_btn -> {
                Utils.addReplaceFragment(
                    requireContext(),
                    BasicAddFragment(),
                    R.id.nav_container1,
                    true,
                    false,
                    false
                )
            }
            R.id.next_btn_ads2 -> {
                checkValidationAds2()


            }
        }
    }

    fun checkValidationAds2() {
        if (propertyFilling.poessionType.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "poession is empty")
        } else if (propertyFilling.area.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "area is empty ")
        } else if (propertyFilling.postedby.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "post by is empty")
        } else if (propertyFilling.amenties!!.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "amenties is empty ")
        } else if (propertyFilling.age_of_property.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Age of Property is empty ")
        } else if (propertyFilling.entrance.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Entrance is empty ")
        } else if (propertyFilling.furnish.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Furnish is empty ")
        } else if (show_bed.text.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Number of Bed is empty ")
        } else if (show_bathroom.text.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Number of Bathroom is empty ")
        } else if (show_sqft.text.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Enter Sqft of property")
        } else {
            //Clear Duplicate Values
            val hashSet = HashSet<Int>()
            hashSet.addAll(propertyFilling.amenties)
            propertyFilling.amenties.clear()
            propertyFilling.amenties.addAll(hashSet)
            val amenities = propertyFilling.amenties

            Log.e("RR", amenities.toString())
            val profileHashMap = HashMap<String, Any>()
            profileHashMap.put(Keys.STEP, "profile")

            if (propertyFilling.edit_flag) {
                profileHashMap.put(Keys.PROPERTY_ID, propertyFilling.editpost?.id.toString())
            }
            profileHashMap.put(Keys.PROPERTY_ID, propertyFilling.propertyID)
            profileHashMap.put(Keys.POSSESSION_TYPE, propertyFilling.poessionType.toString().trim())
            profileHashMap.put(Keys.PROPERTY_PROFILE_AREA, propertyFilling.sqft.toString().trim())
            profileHashMap.put(Keys.PROPERTY_NO_BED, propertyFilling.no_bed)
            profileHashMap.put(Keys.PROPERTY_BATH_ROOM, propertyFilling.no_bathroom)
            profileHashMap.put(Keys.AREA_TYPE, propertyFilling.area)
            profileHashMap.put(Keys.POSTED_BY, propertyFilling.postedby.toString().trim())
            profileHashMap.put(Keys.AMENITIES, amenities)
            profileHashMap.put(Keys.AGE_OF_PROPERTY, propertyFilling.age_of_property.trim())
            profileHashMap.put(Keys.ENTRANCE, propertyFilling.entrance)
            profileHashMap.put(Keys.FURNISHING, propertyFilling.furnish)



            dealerAddRequiredActivity2!!.your_state_progress_bar_id.setCurrentStateNumber(
                StateProgressBar.StateNumber.THREE
            )


//            //for dummy tranfor ===============================================================================check
//            Utils.addReplaceFragment(
//                requireContext(),
//                PhotoUploadAddFragment(),
//                R.id.nav_container1,
//                true,
//                true,
//                true
//            )


//            APi
            serviceViewModel.postserviceBody(
                Keys.ADD_PROPERTY_END_POINT,
                requireContext(),
                profileHashMap,
                Keys.ADD_PROFILE_PROPERTY_RED_CODE,
                true,
                token,
                true,
                this
            )
        }
    }


    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {

            Keys.ADD_PROFILE_PROPERTY_RED_CODE -> {


                val propertySuccessModelResponse =
                    gson.fromJson(response, PropertyProfileModelSuccessfully::class.java)
                Utils.addReplaceFragment(
                    requireContext(),
                    PhotoUploadAddFragment(),
                    R.id.nav_container1,
                    true,
                    true,
                    true
                )
                dealerAddRequiredActivity2!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.THREE
                )
            }


            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(next_btn_ads2, errorModel.message.toString())
            }
        }
    }

    fun sqftSlider() {
        sqft_input.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
            }
        })

        sqft_input.addOnChangeListener { slider, value, fromUser ->

            show_sqft.text = value.toString() + "-sqft"
            propertyFilling.sqft = value

            // Responds to when slider's value is changed
        }
    }

    fun bedSlider() {
        ads_beds.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
            }
        })

        ads_beds.addOnChangeListener { slider, value, fromUser ->
            show_bed.text = value.toInt().toString() + "-Bed"
            propertyFilling.no_bed = value

            // Responds to when slider's value is changed
        }
    }

    fun bathroomSlider() {
        ads_bathroom.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
            }
        })

        ads_bathroom.addOnChangeListener { slider, value, fromUser ->
            show_bathroom.text = value.toInt().toString() + "-Bathroom"
            propertyFilling.no_bathroom = value

            // Responds to when slider's value is changed
        }
    }

}