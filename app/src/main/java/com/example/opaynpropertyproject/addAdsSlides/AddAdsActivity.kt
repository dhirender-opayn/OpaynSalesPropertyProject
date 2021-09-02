package com.example.opaynpropertyproject.addAdsSlides

import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.Utils



class AddAdsActivity : BaseActivity(), View.OnClickListener {
    // steps on state progress bar
    var descriptionData =
        arrayOf("BASIC DETAILS", "PROPERTIES PROFILE", "UPLOAD IMAGES", "PRICE DETAILS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ads)

        //button.setOnClickListener(this)
        supportActionBar!!.hide()
        val fragment = AddAdsFragment1()
        Utils.addReplaceFragment(this,fragment,R.id.nav_container1,true,true,true)



    }

    override fun onClick(v: View?) {
        when (v!!.id) {

//            R.id.button -> {
//
//
//                when (your_state_progress_bar_id.getCurrentStateNumber()) {
//                    1 -> {
//                        your_state_progress_bar_id.setStateDescriptionData(descriptionData)
//                        your_state_progress_bar_id.setCurrentStateNumber(StateProgressBar.StateNumber.TWO)
//                    }
//                    2 -> {
//                        your_state_progress_bar_id.setStateDescriptionData(descriptionData)
//                        your_state_progress_bar_id.setCurrentStateNumber(StateProgressBar.StateNumber.THREE)
//                    }
//                    3 -> {
//                        your_state_progress_bar_id.setStateDescriptionData(descriptionData)
//                        your_state_progress_bar_id.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR)
//                    }
//                    4 -> {
//                        your_state_progress_bar_id.setStateDescriptionData(descriptionData)
//                        your_state_progress_bar_id.setAllStatesCompleted(true)
//                    }
//
//                }
//            }
        }
    }
}