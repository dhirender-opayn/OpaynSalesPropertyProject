package com.example.opaynpropertyproject

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.`interface`.GetPositionInterface
import com.example.opaynpropertyproject.adapter.property_setup_adapters.SearchRecyclerAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SearchModelSuccess
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.customer.CustomerHomeActivity.Companion.token
import com.example.opaynpropertyproject.customer.FilterPropertyActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity

import com.example.opaynpropertyproject.home_activity.SelectedPropertyActivity
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.fragment_search_bar.*
import kotlinx.android.synthetic.main.toolbar.*


class SearchBarFragment : BaseFragment(), View.OnClickListener, ApiResponse, GetPositionInterface {
    lateinit var activity_search: Activity
    var searchList = ArrayList<SearchModelSuccess.Data.Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_bar, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchListner()
        SearchHeader()
        SearchClick()
    }
    private fun SearchClick(){
       activity_search.header_filer.setOnClickListener(this)
    }
    private fun SearchHeader() {
        if (Keys.isCustomer){
            activity_search = requireContext() as CustomerHomeActivity
        } else{
            activity_search = requireContext() as HomeActivity
        }
        activity_search.ads.visibility = View.INVISIBLE
        activity_search.menu_bar.visibility = View.VISIBLE
        activity_search.search_bar_container.visibility = View.VISIBLE
        activity_search.header_filer.visibility = View.VISIBLE

    }

    private fun searchListner() {
//    val activity = requireActivity() as HomeActivity
        activity?.search_bar?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {


            }
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (!activity?.search_bar?.text.toString().isEmpty()) {
                    val searchHasHmap = HashMap<String, Any>()
                    searchHasHmap.put(Keys.KEYWORD, s.toString())
                    serviceViewModel.getserviceWithKeyword(
                        Keys.PROPERTY_SEARCH_END_POINT,
                        requireContext(),
                        Keys.PROPERTY_SEARCH_REQ_CODE,
                        true,
                        HomeActivity.token,
                        s.toString(),
                        false,
                        this@SearchBarFragment
                    )
                } else {
                    searchList.clear()
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.header_filer -> {
                openA(FilterPropertyActivity::class)
            }
        }

    }


    override fun getPosition(position: Int) {
        val selected_property_id = searchList[position].id
        serviceViewModel.getservice(
            Keys.SELECTED_PROPERTY_END_POINT + { selected_property_id },
            requireContext(),
            Keys.SELECTED_PROPERTY_REQ_CODE,
            true,
            token,
            true,
            this
        )

    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.PROPERTY_SEARCH_REQ_CODE -> {
                searchList.clear()
                val searchModel = gson.fromJson(response, SearchModelSuccess::class.java)
                searchList.addAll(searchModel.data.data)
                rv_search.adapter = SearchRecyclerAdapter(searchList, this)
            }
            Keys.SELECTED_PROPERTY_REQ_CODE -> {
                openA(SelectedPropertyActivity::class)

            }
            Keys.BACKENDERROR -> {


            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!text.isEmpty()){
            Log.e("search_result","text")
        }
    }

    companion object {
        var text = ""
    }


}