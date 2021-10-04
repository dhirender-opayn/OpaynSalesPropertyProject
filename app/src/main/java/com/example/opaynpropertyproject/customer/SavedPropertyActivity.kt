package com.example.opaynpropertyproject.customer

import android.os.Bundle
import android.view.View
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.customerAdapter.SavedPropertyRecyclerAdapter
import com.example.opaynpropertyproject.comman.BaseActivity
import kotlinx.android.synthetic.main.activity_saved_property.*
import kotlinx.android.synthetic.main.toolbar.*


class SavedPropertyActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_property)
        supportActionBar?.hide()

        rv_saved_property.adapter = SavedPropertyRecyclerAdapter()
        savedHeader()
        clickListner()

    }

    private fun clickListner() {
        menu_bar.setOnClickListener(this)
    }


    private fun savedHeader() {
        ads.visibility = View.VISIBLE
        menu_bar.setImageResource(R.drawable.ic_baseline_arrow_back_ios_24)
        ads.setText(getString(R.string.favourite))
        header_filer.visibility = View.INVISIBLE
        menu_bar.visibility = View.VISIBLE
        search_bar_container.visibility = View.INVISIBLE
        notification_count.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.menu_bar -> {
                onBackPressed()
            }
        }
    }


}