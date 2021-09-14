package com.example.opaynpropertyproject.addAdsSlides

import ServiceViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapters.singleton.SingletonObject.propertyFilling
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.Utils
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.fragment_add_ads2.*
import kotlinx.android.synthetic.main.fragment_add_ads3.*
import kotlinx.android.synthetic.main.fragment_add_ads4.*
import java.security.Key


class AddAdsFragment4 : BaseFragment(), ApiResponse ,View.OnClickListener{

    lateinit var addPropertyHasMap: HashMap<String, Any>
    var price = ""
    var property_des = ""
    var addAdsRequiredActivityFinish: AddAdsActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ads4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

         addAdsRequiredActivityFinish = requireActivity() as AddAdsActivity
        finish_btn.setOnClickListener(this)
        pre_btn_finish.setOnClickListener(this)
        addPropertyHasMap = HashMap<String, Any>()
        if (propertyFilling.property_description.isNotEmpty()) {
            price_input.setText(propertyFilling.property_price)
            property_description.setText(propertyFilling.property_description)
        } else {
           checkValidationPricing()

        }


    }

    fun checkValidationPricing(){
        if (price_input.text.toString().trim().isEmpty()){
            Utils.customSnakebar(finish_btn, "Price is empty")
        } else if(property_description.text.toString().trim().isEmpty()){
             Utils.customSnakebar(finish_btn, "Description is empty")
        }else {
            val id  = 19
            val pricingHashMap = HashMap<String, Any>()
            pricingHashMap.put(Keys.STEP,"pricing")
            pricingHashMap.put(Keys.PROPERTY_ID,id)
            pricingHashMap.put(Keys.PROPERTY_PRICE,price_input.text.toString().trim())
            pricingHashMap.put(Keys.DESCRIPTION,property_description.text.toString().trim())
            pricingHashMap.put(Keys.STATUS,true)
            price = price_input.text.toString()
            property_des = property_description.text.toString()
            propertyFilling.property_price = price
            propertyFilling.property_description = property_des
            serviceViewModel.postservice(Keys.ADD_PROPERTY_END_POINT,requireContext(),pricingHashMap,Keys.ADD_PROFILE_PROPERTY_PRICING_CODE,true,token,true,this)
        }

    }
    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.ADD_PROFILE_PROPERTY_PRICING_CODE ->{
                Utils.customSnakebar(finish_btn,"Property is setup is finish")
                addAdsRequiredActivityFinish!!.your_state_progress_bar_id.setCurrentStateNumber(
                    StateProgressBar.StateNumber.FOUR
                )
                Utils.customSnakebar(finish_btn,"Property Setup is Finish !!")

            }
            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                Utils.customSnakebar(finish_btn, errorModel.message)
            }

        }

    }

    override fun onClick(v: View?) {
       when(v!!.id){
           R.id.finish_btn -> {
               checkValidationPricing()
           }
           R.id.pre_btn_finish ->{
               Utils.addReplaceFragment(
                   requireContext(),
                   AddAdsFragment3(),
                   R.id.nav_container1,
                   true,
                   false,
                   false
               )
           }
       }
    }
}