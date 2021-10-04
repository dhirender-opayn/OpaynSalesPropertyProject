package com.example.opaynpropertyproject.customer

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.adapter.customerAdapter.NotificationRecyclerAdapter
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.toolbar.*


class NotificationFragment : Fragment() {
    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NotificationHeader()
        rv_notification.adapter = NotificationRecyclerAdapter()
    }


    private fun NotificationHeader() {
        if (Keys.isCustomer) {
            activity = requireContext() as CustomerHomeActivity
        } else {
              activity = requireContext() as HomeActivity
        }

        activity.ads.visibility = View.VISIBLE
        activity.ads.setText(getString(R.string.notification))
        activity.menu_bar.visibility = View.VISIBLE
        activity.search_bar_container.visibility = View.INVISIBLE
    }


}