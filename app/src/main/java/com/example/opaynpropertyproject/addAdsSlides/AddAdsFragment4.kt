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
import com.example.opaynpropertyproject.comman.Utils
import com.kofigyan.stateprogressbar.StateProgressBar
import kotlinx.android.synthetic.main.activity_add_ads.*
import kotlinx.android.synthetic.main.fragment_add_ads2.*
import kotlinx.android.synthetic.main.fragment_add_ads4.*
import java.security.Key


class AddAdsFragment4 : Fragment(), ApiResponse ,View.OnClickListener{
    val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2JlYzA3NGU1M2FkZDM2MzI4MjZiMmM2NzY3ZGQ3ZWQxZDViODk0MGI3MjM3ZmZkYWM3ZGRhNzA1Y2M1MjJjYmYyZTM3NWI2OWUwY2FjY2QiLCJpYXQiOjE2MzE1MjAxMTcuODcxODAxLCJuYmYiOjE2MzE1MjAxMTcuODcxODAyLCJleHAiOjE2NjMwNTYxMTcuODY1MzY5LCJzdWIiOiIyMyIsInNjb3BlcyI6W119.cZEFoaZRxIuOe2dvshC39g9pHYyja5pwk_9uEBfjk8_3o58ikbXt5c1QOwbGmZjjTiMIgRXwJeHy_xCJc3qSeAPTgzwV4Ce9ZmJZybYAKoz7-Xn218P3Bu5qOCTUOTwTxdoLRa1uSyUCe6zsvNrBe8MNSSBV-6T9GcS6Ro3JB6VwSCahlkQ2rhdTaIwqeN-NcTznscyUHQvcK74qVqX4Cz6H8R1OssF0HSdXHKv-TbIaZkISlmd-mJx3JxWf8JqMFd7HB8ZJtgJilpaOGNwntw2z6L-BMvFyeDx1BySpkNaWJtvO8YTA_dLJ2MjNaS47brsf97m1puasHysU0HDdF9Cz8nUM8HvBQcXUcbUksOTyr6CxDXNqi2X5iawfzGcrg8iA_isyHYM_qhZJqjxSnFU530Bm02UoKps_VXLB1W62VdQ634xXnxtpU6dn9NYNWD0AbHXS52giX2YXRFlbjjUf0EWardZI-cBPw6wn2Gj1yncTFiz0D25JyJNfdvk3RhkpbmWTccBqSDF4M1LuPxpUqC6vbhWPrP9aNxFT2DVT1_BYL6gcDbnaDELHfE9pl4ZES664SbjAiaIKdF4y-bdqd7P1awYnzypYNB0LNhDM-B-CgETCl-2q6ZLlXr87nYr0AWIEElzTtfbbgkEyWBkY8vuIOsWCV4kdAKaF8b0"

    lateinit var serviceViewModel: ServiceViewModel
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
        serviceViewModel = ServiceViewModel()
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

        }

    }

    override fun onClick(v: View?) {
       when(v!!.id){
           R.id.finish_btn -> {
               checkValidationPricing()
           }
       }
    }
}