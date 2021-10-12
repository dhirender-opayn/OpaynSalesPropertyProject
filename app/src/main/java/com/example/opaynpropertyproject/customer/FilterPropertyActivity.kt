package com.example.opaynpropertyproject.customer

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.SearchBarFragment
import com.example.opaynpropertyproject.adapter.customerAdapter.*
import com.example.opaynpropertyproject.adapter.property_setup_adapters.PossessionStatusRecyclerAdaper
import com.example.opaynpropertyproject.adapter.property_setup_adapters.SellerTypeRecyclerViewAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.FilterModel
import com.example.opaynpropertyproject.api_model.SellPropertyModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.singleton.SingletonObject
import io.reactivex.Single
import kotlinx.android.synthetic.main.basic_add_fragment.*
import kotlinx.android.synthetic.main.fragment_filter_property.*
import kotlinx.android.synthetic.main.pricing_add_fragment.*
import kotlinx.android.synthetic.main.profile_add_fragment.*
import kotlinx.android.synthetic.main.toolbar2.*
import kotlinx.android.synthetic.main.toolbar2.view.*


class FilterPropertyActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    var filterHashmap: HashMap<String, Any>? = null
    var filter_property_model: FilterModel? = null
    var sell_model: SellPropertyModel? = null
    var filter_sellType_list = listOf<FilterModel.Data.Option>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_filter_property)
        overridePendingTransition(0, 0)
        filterHeader()
        click()
        filterSellApi(HomeActivity.token, this)
        spinnerListner()

    }

    private fun spinnerListner() {
        filter_price.addOnChangeListener { slider, value, fromUser ->
            filter_show_price.text = "$ " + value.toInt().toString()
            SingletonObject.propertyFilling.no_bed = value
        }
        filter_sqft_input.addOnChangeListener { slider, value, fromUser ->
            filter_show_sqft.text =  value.toInt().toString() + " sqft"
            SingletonObject.propertyFilling.no_bed = value
        }
        filer_beds.addOnChangeListener { slider, value, fromUser ->
            filer_show_bed.text = value.toInt().toString() + " bed"
            SingletonObject.propertyFilling.no_bed = value
        }
        filter_bathroom.addOnChangeListener { slider, value, fromUser ->
              filter_show_bathroom.text = value.toInt().toString() + " bathroom"
            SingletonObject.propertyFilling.no_bed = value
        }


    }

    fun filterSellApi(token: String, responseListener: ApiResponse) {
        serviceViewModel.getservice(
            Keys.PROPERTY_TYPE_DETAIL,
            this,
            Keys.PROPERTY_TYPE_REQ_CODE,
            true,
            token,
            true,
            responseListener
        )
    }

    private fun filterHeader() {
        supportActionBar?.hide()
        filter_toolbar.menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        ads.ads.setText(getString(R.string.filter))
        header_filer.visibility = View.INVISIBLE

    }


    private fun FilterApi() {
        val sellType = SingletonObject.propertyFilling.sell_type
        val property_type = SingletonObject.propertyFilling.property_type
        val possession_Type = SingletonObject.propertyFilling.poessionType
        val area_type = SingletonObject.propertyFilling.area
        val posted_by = SingletonObject.propertyFilling.postedby
        val amenties = SingletonObject.propertyFilling.amenties
        val age_property = SingletonObject.propertyFilling.age_of_property
        val enterance = SingletonObject.propertyFilling.entrance
        val furnished = SingletonObject.propertyFilling.furnish
        val price = filter_price.value.toString()
        filter_show_price.setText(price)
        val sqft = filter_sqft_input.value.toString()
        val filter_beds = filer_beds.value.toString()
        val filterBathroom = filter_bathroom.value.toString()
        filterHashmap = HashMap<String, Any>()
        filterHashmap!!.put(Keys.KEYWORD, "")
        filterHashmap!!.put(Keys.PROPERTY_PRICE, price)
        filterHashmap!!.put(Keys.PROPERTY_PROFILE_AREA, sqft)
        filterHashmap!!.put(Keys.PROPERTY_NO_BED, filter_beds)
        filterHashmap!!.put(Keys.PROPERTY_BATH_ROOM, filterBathroom)
        filterHashmap!!.put(Keys.SELL_Type, sellType)
        filterHashmap!!.put(Keys.POSTED_BY, posted_by)
        filterHashmap!!.put(Keys.AREA_TYPE, area_type)
        filterHashmap!!.put(Keys.ENTRANCE, enterance)
        filterHashmap!!.put(Keys.FURNISHING, furnished)


        serviceViewModel.getserviceWithKeyword2(
            Keys.PROPERTY_SEARCH_END_POINT,
            this,
            Keys.PROPERTY_SEARCH_REQ_CODE,
            true,
            CustomerHomeActivity.token,
            filterHashmap!!,
            true,
            this
        )
    }

    private fun click() {
        menu_bar.setOnClickListener(this)
        apply_filter_btn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.menu_bar -> {
                onBackPressed()
            }
            R.id.apply_filter_btn -> {
                FilterApi()
            }
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.PROPERTY_TYPE_REQ_CODE -> {
                //check index is error or not ???
                filter_property_model = gson.fromJson(response, FilterModel::class.java)


                filter_sellType_list = filter_property_model!!.data[0].options
                rv_filter_sell_type.adapter = FilterSellTypeAdapter(filter_sellType_list)

                val filter_property_type_list = filter_property_model!!.data[1].options
                rv_filter_property_type.adapter =
                    FilterPropertyTypeAdapter(filter_property_type_list)

                val filter_possession_list = filter_property_model!!.data[1].options
                rv_filter_possession_list.adapter = FilterPossessionAdapter(filter_possession_list)


                val filter_area_list = filter_property_model!!.data[2].options
                rv_filter_area.adapter = FilterAreaAdapter(filter_area_list, this)

                val filter_postedby_list = filter_property_model!!.data[3].options
                rv_filter_posted_by.adapter = FilterPostedByAdapter(filter_postedby_list, this)


                val filter_amenties = filter_property_model!!.data[7].options
                rv_filter_amenities.adapter = FilterAmenitiesAdapter(filter_amenties, this)

                val filter_age_of_property_list = filter_property_model!!.data[4].options
                rv_filter_age_of_property.adapter =
                    FilterAgePropertyAdapter(filter_age_of_property_list, this)

                val filter_entranceList = filter_property_model!!.data[5].options
                rv_filter_entrance.adapter = FilterEntranceAdapter(filter_entranceList, this)

                val filter_furnishList = filter_property_model!!.data[6].options
                rv_filter_furnishing.adapter = FilterFurnishAdapter(filter_furnishList, this)
            }
            Keys.PROPERTY_SEARCH_REQ_CODE -> {
                SearchBarFragment.text = "1"
            }

        }
    }
}