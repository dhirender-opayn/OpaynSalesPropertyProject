package com.example.opaynpropertyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar.*

class AboutUsActivity : AppCompatActivity() ,View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        aboutHeader()
        setClick()
    }
    private fun setClick(){
        menu_bar.setOnClickListener(this)
    }
    private fun aboutHeader() {
        supportActionBar!!.hide()
        ads.visibility = View.VISIBLE
        menu_bar.visibility = View.VISIBLE
        ads.setText(getString(R.string.about_us))
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
}