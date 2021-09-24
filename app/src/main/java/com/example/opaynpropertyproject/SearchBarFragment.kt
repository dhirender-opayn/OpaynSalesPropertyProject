package com.example.opaynpropertyproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.adapter.property_setup_adapters.SearchRecyclerAdapter
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.SearchModel
import com.example.opaynpropertyproject.api_model.SearchModelSuccess
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.home_activity.HomeActivity
import kotlinx.android.synthetic.main.activity_account_setting.*
import kotlinx.android.synthetic.main.fragment_search_bar.*
import kotlinx.android.synthetic.main.toolbar.*


class SearchBarFragment : BaseFragment(),View.OnClickListener,ApiResponse {
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

    }

    private fun searchListner() {
    val activity = requireActivity() as HomeActivity
       activity.search_bar.addTextChangedListener(object : TextWatcher {

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
                if (!activity.search_bar.text.toString().isEmpty())
                {
                    val searchHasHmap = HashMap<String, Any>()
                    searchHasHmap.put("keyword", s.toString())
                    serviceViewModel.getserviceWithKeyword(
                        Keys.PROPERTY_SEARCH_END_POINT,
                       activity.applicationContext,
                        Keys.PROPERTY_SEARCH_REQ_CODE,
                        true,
                        HomeActivity.token,
                        s.toString(),
                        false,
                        this@SearchBarFragment
                    )
                }
                else {
                    searchList.clear()
                }
            }
        })
    }
    override fun onClick(v: View?) {

    }

    override fun onResponse(requestcode: Int, response: String) {
        when(requestcode) {
            Keys.PROPERTY_SEARCH_REQ_CODE -> {
                searchList.clear()
                val searchModel = gson.fromJson(response, SearchModelSuccess::class.java)
                searchList.addAll(searchModel.data.data)
                rv_search.adapter = SearchRecyclerAdapter(searchList)

            }
            Keys.BACKENDERROR -> {


            }
        }
    }


}