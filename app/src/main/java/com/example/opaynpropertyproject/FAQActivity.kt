package com.example.opaynpropertyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.FAQSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import kotlinx.android.synthetic.main.activity_faqactivity.*
import kotlinx.android.synthetic.main.toolbar.*

class FAQActivity : BaseActivity(),View.OnClickListener ,ApiResponse{
    var faq_list = ArrayList<FAQSuccessModel.Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_faqactivity)
        faqHeader()
        setClick()
        faqApiCall()

    }


    private fun faqApiCall(){
        serviceViewModel.getservice(Keys.FAQ_END_POINT,this,Keys.FAQ_REQ_CODE,true, token,true,this)
    }
    private fun setClick(){
        menu_bar.setOnClickListener(this)
    }

    private fun faqHeader() {
        supportActionBar!!.hide()
        ads.visibility = View.VISIBLE
        menu_bar.visibility = View.VISIBLE
        ads.setText(getString(R.string.faq))
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        notification_count.visibility = View.INVISIBLE
        search_bar_container.visibility = View.INVISIBLE
        header_filer.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.menu_bar -> {
                onBackPressed()
            }
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when(requestcode){
            Keys.FAQ_REQ_CODE -> {
                val faqModel = gson.fromJson(response,FAQSuccessModel::class.java)
                faq_list.addAll(faqModel.data)
                rv_faq.adapter = FaqRecyclerViewAdapter(faq_list, this)


            }
        }

    }

}