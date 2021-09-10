package com.example.opaynpropertyproject.addAdsSlides

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.ads_adapters.*
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.api_model.StateModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.fragment_add_ads1.*
import kotlinx.android.synthetic.main.fragment_add_ads2.*
import kotlinx.android.synthetic.main.posted_by_view_holder.*
import ServiceViewModel
import android.widget.Toast
import com.example.opaynpropertyproject.api_model.ErrorModel
import kotlinx.android.synthetic.main.activity_sign_up.*


class AddAdsFragment2 : BaseFragment(), View.OnClickListener, ApiResponse {

    var bundle = Bundle()
    var sellPropertyBundle: SellPropertyModel? = null
    var sell_list = listOf<SellPropertyModel.Data.Option>()
    var area_list = listOf<SellPropertyModel.Data.Option>()
    var postedby_list = listOf<SellPropertyModel.Data.Option>()
    var amenities_list = listOf<SellPropertyModel.Data.Option>()
    var age_of_property_list = listOf<SellPropertyModel.Data.Option>()
    var entranceList = listOf<SellPropertyModel.Data.Option>()
    var furnishList = listOf<SellPropertyModel.Data.Option>()
    var sqft: String = ""
    var number_bed: String = ""
    var number_bathroom: String = ""
    var addAdsRequiredActivity2: AddAdsActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ads2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        addAdsRequiredActivity2 = requireActivity() as AddAdsActivity

        if (propertyFilling.number_bed_rev!!.isNotEmpty()) { // if user press back button
            setDataAdsFragement2()

        } else {
            startingFragment2()
        }


    }

    fun setDataAdsFragement2() {
        bundle = requireArguments()
        sellPropertyBundle = bundle.getParcelable<SellPropertyModel>(Keys.ADS_DATA)
        addAdsRequiredActivity2!!.your_state_progress_bar_id.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)

        //sqft area

        sqft_input.setText(propertyFilling.sqft)
        ads_beds.setText(propertyFilling.no_bed)
        ads_bathrrom.setText(propertyFilling.no_bathroom)

        //sell list
        sell_list = propertyFilling.sell_list_rev!!
        rv_possession_list.adapter = PossessionStatusRecyclerAdaper(sell_list, requireContext())
        //area list
        area_list = propertyFilling.area_list_rev!!
        rv_area.adapter = AreaRecyclerViewAdapter(area_list, requireContext())
        //postedby list
        postedby_list = propertyFilling.postedby_list_rev!!
        rv_posted_by.adapter = PostedByRecyclerAdapter(postedby_list, requireContext())
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

    fun startingFragment2() {
        pre_btn.setOnClickListener(this)
        bundle = requireArguments()
        propertyFilling.AdsAdd2Bundle = bundle
        sellPropertyBundle = bundle.getParcelable<SellPropertyModel>(Keys.ADS_DATA)
        HitAdsApi()

        next_btn_ads2.setOnClickListener(this)

    }


    fun HitAdsApi() {

//        for (item in 0..bundle.size()){
//         if (2 == sellPropertyBundle!!.data[item].id  ){
//             rv_area.adapter = AreaRecyclerViewAdapter(area_list, requireContext())
//         }
//        }

        //possession types
        sell_list = sellPropertyBundle!!.data[1].options
        rv_possession_list.adapter = PossessionStatusRecyclerAdaper(sell_list, requireContext())
        propertyFilling.sell_list_rev = sell_list

        //Area
        area_list = sellPropertyBundle!!.data[2].options
        rv_area.adapter = AreaRecyclerViewAdapter(area_list, requireContext())
        propertyFilling.area_list_rev = area_list

        //posted By
        postedby_list = sellPropertyBundle!!.data[3].options
        rv_posted_by.adapter = PostedByRecyclerAdapter(postedby_list, requireContext())
        propertyFilling.postedby_list_rev = postedby_list

        //Amenities
        amenities_list = sellPropertyBundle!!.data[7].options
        rv_amenities.adapter = AmenitiesRecyclerAdapter(amenities_list, requireContext())
        propertyFilling.amenities_list_rv = amenities_list

        //age of property
        age_of_property_list = sellPropertyBundle!!.data[4].options
        rv_age_of_property.adapter = AgeOfPropertyAdapter(age_of_property_list, requireContext())
        propertyFilling.age_of_property_rv = age_of_property_list

        //Entrance
        entranceList = sellPropertyBundle!!.data[5].options
        rv_entrance.adapter = EntranceRecyclerAdapter(entranceList, requireContext())
        propertyFilling.entranceList_rev = entranceList

        //Furnishing
        furnishList = sellPropertyBundle!!.data[6].options
        rv_furnishing.adapter = FurnishRecyclerAdapter(furnishList, requireContext())
        propertyFilling.furnishList_rev = furnishList

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pre_btn -> {
                Utils.addReplaceFragment(
                    requireContext(),
                    AddAdsFragment1(),
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
        if (SingletonObject.propertyFilling.poessionType.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "poession is empty")
        } else if (SingletonObject.propertyFilling.area.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "area is empty ")
        } else if (SingletonObject.propertyFilling.postedby.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "post by is empty")
        } else if (SingletonObject.propertyFilling.amenties!!.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "amenties is empty ")
        } else if (SingletonObject.propertyFilling.age_of_property.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Age of Property is empty ")
        } else if (SingletonObject.propertyFilling.entrance.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Entrance is empty ")
        } else if (SingletonObject.propertyFilling.furnish.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Furnish is empty ")
        } else if (sqft_input.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "SQFT is empty ")
        } else if (ads_beds.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Number of Bed is empty ")
        } else if (ads_bathrrom.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "Number of Bathroom is empty ")
        } else {
            SingletonObject.propertyFilling.sqft = sqft_input.text.toString()
            SingletonObject.propertyFilling.no_bed = ads_beds.text.toString()
            SingletonObject.propertyFilling.no_bathroom = ads_bathrrom.text.toString()

            Log.e("testtest", propertyFilling.testID.toString())


            //Clear Duplicate Values
            val hashSet = HashSet<Int>()
            hashSet.addAll(propertyFilling.amenties)
            propertyFilling.amenties.clear()
            propertyFilling.amenties.addAll(hashSet)
            val amenities = propertyFilling.amenties

            Log.e("RR", amenities.toString())
            val profileHashMap = HashMap<String, Any>()
            profileHashMap.put(Keys.STEP, "profile")
            profileHashMap.put(Keys.PROPERTY_ID, propertyFilling.propertyID)
            profileHashMap.put(Keys.PROPERTY_PROFILE_AREA, sqft_input.text.toString().trim())
            profileHashMap.put(Keys.AMENITIES, amenities)


            //APi
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
                Utils.addReplaceFragment(
                    requireContext(),
                    AddAdsFragment3(),
                    R.id.nav_container1,
                    true,
                    true,
                    true
                )
                addAdsRequiredActivity2!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.THREE
                )
            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(next_btn_ads2,errorModel.message.toString())
        }
    }
}

}