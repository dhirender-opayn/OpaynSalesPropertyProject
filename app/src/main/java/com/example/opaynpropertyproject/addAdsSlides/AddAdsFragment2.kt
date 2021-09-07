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
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.api_model.StateModel
import com.example.opaynpropertyproject.comman.Utils
import kotlinx.android.synthetic.main.fragment_add_ads1.*
import kotlinx.android.synthetic.main.fragment_add_ads2.*
import kotlinx.android.synthetic.main.posted_by_view_holder.*

class AddAdsFragment2 : Fragment(), View.OnClickListener {

    var bundle = Bundle()
    var sellPropertyBundle: SellPropertyModel? = null
    var sell_list = listOf<SellPropertyModel.Data.Option>()
    var area_list = listOf<SellPropertyModel.Data.Option>()
    var postedby_list = listOf<SellPropertyModel.Data.Option>()
    var sqft: String = ""
    var number_bed: String = ""
    var number_bathroom: String = ""
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

        pre_btn.setOnClickListener(this)
        bundle = requireArguments()
        sellPropertyBundle = bundle.getParcelable<SellPropertyModel>(Keys.ADS_DATA)
        HitAdsApi()

        next_btn_ads2.setOnClickListener(this)
        sqft_btn.setOnClickListener(this)
        ads_bed_btn.setOnClickListener(this)
        ads_bathroom_btn.setOnClickListener(this)


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

        //Area
        area_list = sellPropertyBundle!!.data[2].options
        rv_area.adapter = AreaRecyclerViewAdapter(area_list, requireContext())

        //posted By
        postedby_list = sellPropertyBundle!!.data[3].options
        rv_posted_by.adapter = PostedByRecyclerAdapter(postedby_list, requireContext())

        //Amenities
        val amenities_list = sellPropertyBundle!!.data[7].options
        rv_amenities.adapter = AmenitiesRecyclerAdapter(amenities_list, requireContext())

        //age of property
        val age_of_property_list = sellPropertyBundle!!.data[4].options
        rv_age_of_property.adapter = AgeOfPropertyAdapter(age_of_property_list, requireContext())

        //Entrance
        val entranceList = sellPropertyBundle!!.data[5].options
        rv_entrance.adapter = EntranceRecyclerAdapter(entranceList, requireContext())

        //Furnishing
        val furnishList = sellPropertyBundle!!.data[6].options
        rv_furnishing.adapter = FurnishRecyclerAdapter(furnishList, requireContext())

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
            R.id.sqft_btn -> {
                sqft = sqft_input.text.toString()
                if (sqft.isNotEmpty()) {
                    sqft_show.text = sqft + " sqft"
                    SingletonObject.propertyFilling.sqft = sqft_show.text.toString()
                } else {
                    Log.e("sqft", "Please Enter SQFT")
                }


            }
            R.id.ads_bed_btn -> {
                number_bed = ads_beds.text.toString()
                if (number_bed.isNotEmpty()) {
                    bed_show.text = number_bed + " Bed"
                    SingletonObject.propertyFilling.no_bed = bed_show.text.toString()
                } else {
                    Log.e("bed", "Please Enter bed")
                }


            }
            R.id.ads_bathroom_btn -> {
                number_bathroom = ads_bathrrom.text.toString()
                if (number_bathroom.isNotEmpty()) {
                    bathroom_show.text = number_bathroom + " Bathroom"
                    SingletonObject.propertyFilling.no_bathroom = bathroom_show.text.toString()
                } else {
                    Log.e("Bathroom", "Please Enter Bathroom")
                }
            }
            R.id.next_btn_ads2 -> {
                checkValidationAds2()


            }
        }
    }

    fun checkValidationAds2() {
        if (SingletonObject.propertyFilling.poessionType.isEmpty() ) {
            Utils.customSnakebar(next_btn_ads2, "poession is empty")
        } else if(SingletonObject.propertyFilling.area.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "area is empty ")
        } else if (SingletonObject.propertyFilling.postedby.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "post by is empty")
        }else if (SingletonObject.propertyFilling.amenties.isEmpty()) {
            Utils.customSnakebar(next_btn_ads2, "amenties is empty ")
        }else if (SingletonObject.propertyFilling.age_of_property.isEmpty()){
            Utils.customSnakebar(next_btn_ads2, "Age of Property is empty ")
        } else if (SingletonObject.propertyFilling.entrance.isEmpty()){
            Utils.customSnakebar(next_btn_ads2, "Entrance is empty ")
        }else if (SingletonObject.propertyFilling.furnish.isEmpty()){
            Utils.customSnakebar(next_btn_ads2, "Furnish is empty ")
        }else if (SingletonObject.propertyFilling.sqft.isEmpty()){
            Utils.customSnakebar(next_btn_ads2, "SQFT is empty ")
        }else if (SingletonObject.propertyFilling.no_bed.isEmpty()){
            Utils.customSnakebar(next_btn_ads2, "Number of Bed is empty ")
        }else if(SingletonObject.propertyFilling.no_bathroom.isEmpty()){
            Utils.customSnakebar(next_btn_ads2, "Number of Bathroom is empty ")
        } else {
            SingletonObject.propertyFilling.sqft = sqft
            SingletonObject.propertyFilling.no_bed = number_bed
            SingletonObject.propertyFilling.no_bathroom = number_bathroom
        }
    }

}