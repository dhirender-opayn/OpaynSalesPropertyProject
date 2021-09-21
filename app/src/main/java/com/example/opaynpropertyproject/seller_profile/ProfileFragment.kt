package com.example.opaynpropertyproject.seller_profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.login_signup_activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.reflect.KClass


class ProfileFragment : BaseFragment(),View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setclicks()
    }


    private  fun setclicks()
    {
        logout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
         when(v!!.id)
         {
             R.id.logout->{
                  SharedPreferenceManager(requireContext()).saveString(Keys.USERID,"")
                 SharedPreferenceManager(requireContext()).getString(Keys.USERID).let {
                     if (it==null||it.toString().equals("0"))
                     {

                     }
                     else{

                     }
                 }
                   openA(LoginActivity::class)

             }
         }
    }
}