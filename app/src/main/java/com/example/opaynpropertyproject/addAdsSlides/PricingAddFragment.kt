package com.example.opaynpropertyproject.addAdsSlides

import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.os.HandlerCompat.postDelayed
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.pricing_add_fragment.*


class PricingAddFragment : BaseFragment(), ApiResponse, View.OnClickListener {

    lateinit var addPropertyHasMap: HashMap<String, Any>
    var price = ""
    var property_des = ""
    var dealerAddRequiredActivityFinish: DealerAddActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pricing_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dealerAddRequiredActivityFinish = requireActivity() as DealerAddActivity
        finish_btn.setOnClickListener(this)
        pre_btn_finish.setOnClickListener(this)
        addPropertyHasMap = HashMap<String, Any>()
        if (propertyFilling.property_description.isNotEmpty()) {
            price_input.setText(propertyFilling.property_price)
            property_description.setText(propertyFilling.property_description)
        }
        if (propertyFilling.edit_flag) {
            setdata()
        }
    }

    //  edit post data
    private fun setdata() {
        price_input.setText(propertyFilling.detailModel?.data?.get(0)!!.price.toString())
        property_description.setText(propertyFilling.detailModel?.data?.get(0)!!.description.toString())
    }

    fun checkValidationPricing() {
        if (price_input.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(finish_btn, "Price is empty")
        } else if (property_description.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(finish_btn, "Description is empty")
        } else {
            val pricingHashMap = HashMap<String, Any>()
            pricingHashMap.put(Keys.STEP, "pricing")
            pricingHashMap.put(Keys.PROPERTY_ID, propertyFilling.propertyID.toString())
            pricingHashMap.put(Keys.PROPERTY_PRICE, price_input.text.toString().trim())
            pricingHashMap.put(Keys.DESCRIPTION, property_description.text.toString().trim())
            pricingHashMap.put(Keys.STATUS, true)
            price = price_input.text.toString()
            property_des = property_description.text.toString()
            propertyFilling.property_price = price
            propertyFilling.property_description = property_des
            serviceViewModel.postservice(
                Keys.ADD_PROPERTY_END_POINT,
                requireContext(),
                pricingHashMap,
                Keys.ADD_PROFILE_PROPERTY_PRICING_CODE,
                true,
                token,
                true,
                this
            )

        }

    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.ADD_PROFILE_PROPERTY_PRICING_CODE -> {


                //send screen to main activity where all property fragment add


                Utils.customSnakebar(finish_btn, "Property is setup is finish")
                dealerAddRequiredActivityFinish!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.FOUR
                )
                Utils.customSnakebar(finish_btn, "Property Setup is Finish !!")
                requireActivity().finish()

            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(finish_btn, errorModel.message)
            }

        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.finish_btn -> {

                checkValidationPricing()
                propertyFilling.edit_flag = false
            }
            R.id.pre_btn_finish -> {

                Utils.addReplaceFragment(
                    requireContext(),
                    PhotoUploadAddFragment(),
                    R.id.nav_container1,
                    true,
                    false,
                    false
                )


            }
        }
    }
}