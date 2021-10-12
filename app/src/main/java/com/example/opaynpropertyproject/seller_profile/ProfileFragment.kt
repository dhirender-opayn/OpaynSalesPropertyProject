package com.example.opaynpropertyproject.seller_profile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opaynpropertyproject.*
import com.example.opaynpropertyproject.addAdsSlides.DealerAddActivity
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api_model.GetProfileSuccess
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseFragment
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.customer.SavedPropertyActivity


import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token

import com.example.opaynpropertyproject.home_activity.SellerAddedAdsProperty
import com.example.opaynpropertyproject.login_signup_activity.LoginActivity
import com.example.opaynpropertyproject.seller_chat.ChatScreenActivity
import com.example.opaynpropertyproject.seller_chat.SellerChatFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.greetupp.extensions.isNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*


class ProfileFragment : BaseFragment(), View.OnClickListener, ApiResponse {
    lateinit var activity: Activity

    private lateinit var googleSignInClient: GoogleSignInClient

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileHeader()
        setclicks()
        getProfileApi()
//       getProfileDeatils()

        //==========================//
        auth = FirebaseAuth.getInstance()
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        checkUser()
    }

    private fun checkUser() {
        //get current user
       val current_user =  auth.currentUser
        if (current_user != null){
           val email =  current_user.email

        }
    }


    private fun profileHeader() {
        if (Keys.isCustomer) {
            activity = requireContext() as CustomerHomeActivity

            add_property.visibility = View.INVISIBLE
            wishlist.visibility = View.VISIBLE
            my_property.visibility = View.INVISIBLE
            customer_message.visibility = View.VISIBLE

        } else {
            activity = requireContext() as HomeActivity
            wishlist.visibility = View.INVISIBLE
            add_property.visibility = View.VISIBLE
            my_property.visibility = View.VISIBLE
            customer_message.visibility = View.INVISIBLE
        }
        activity.ads.visibility = View.VISIBLE
        activity.ads.setText(getString(R.string.profile))
        activity.menu_bar.visibility = View.INVISIBLE
        activity.search_bar_container.visibility = View.INVISIBLE
        activity.header_filer.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        val mGon = SharedPreferenceManager(requireContext()).getString(Keys.USERDATA)
        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
        val mUser_name = model.data.user.name
        val mUser_email = model.data.user.email
        val mUser_mobile = model.data.user.mobile
        if (!model.data.user.profile.image.isNull()){
            Picasso.get().load(model.data.user.profile.image).placeholder(R.drawable.down_arrow).into(profile_img)
            Log.e("checkimage",model.data.user.profile.image.toString())
        }
        profile_name.setText(mUser_name)
        user_email.setText(mUser_email)
        if (mUser_mobile.equals(null)) {
            seller_phone_number.setText("+91-0000000000")
        } else {
            seller_phone_number.setText(mUser_mobile.toString())
        }
    }

    private fun getProfileApi() {
        if (Keys.isCustomer) {
            serviceViewModel.getservice(
                Keys.GET_CUSTOMER_PROFILE_END_POINT,
                requireContext(),
                Keys.GET_CUSTOMER_PROFILE_REQ_CODE,
                true,
                token,
                true,
                this
            )
        } else {
                serviceViewModel.getservice(
                Keys.GET_PROFILE_END_POINT,
                requireContext(),
                Keys.GET_PROFILE_REQ_CODE,
                true,
                token,
                true,
                this
            )
        }
    }

    private fun setclicks() {
        logout.setOnClickListener(this)
        account_setting.setOnClickListener(this)
        add_property.setOnClickListener(this)
        my_property.setOnClickListener(this)
        faq.setOnClickListener(this)
        about.setOnClickListener(this)
        contact_us.setOnClickListener(this)
        wishlist.setOnClickListener(this)
        customer_message.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.logout -> {
                signOut()
                SharedPreferenceManager(requireContext()).saveString(Keys.USERID, "")
                SharedPreferenceManager(requireContext()).getString(Keys.USERID).let {
                    if (it == null || it.toString().equals("0")) {

                    } else {

                    }
                }
                Keys.isCustomer = false
                openA(LoginActivity::class)
            }
            R.id.account_setting -> {
//                val bundle = Bundle()
//                bundle.putParcelable("key")
                openA(AccountSettingActivity::class)
            }
            R.id.add_property -> {
                openA(DealerAddActivity::class)

            }
            R.id.my_property -> {
                openA(SellerAddedAdsProperty::class)
            }
            R.id.faq -> {
                openA(FAQActivity::class)
            }
            R.id.about -> {
                openA(AboutUsActivity::class)
            }
            R.id.contact_us -> {
                openA(ContactUsActivity::class)
            }
            R.id.wishlist -> {
                openA(SavedPropertyActivity::class)
            }
            R.id.customer_message -> {
                Utils.addReplaceFragment(requireContext(),SellerChatFragment(),R.id.nav_container,true,true,true)
            }
        }
    }

    override fun onResponse(requestcode: Int, response: String) {
        when (requestcode) {
            Keys.GET_PROFILE_REQ_CODE -> {
                val get_profile_model = gson.fromJson(response, GetProfileSuccess::class.java)
                val get_profile_list = ArrayList<GetProfileSuccess.Data>()
                get_profile_list.addAll(listOf(get_profile_model.data))
                val mUser_name = get_profile_model?.data!!.user.name
                val mUser_email = get_profile_model.data.user.email
                val mUser_mobile = get_profile_model.data.user.mobile
                Picasso.get().load(get_profile_model.data.user.profile.image).placeholder(R.drawable.down_arrow).into(profile_img)
                profile_name.setText(mUser_name)
                user_email.setText(mUser_email)
                seller_phone_number.setText(mUser_mobile)
//                if (mUser_mobile.equals(null)) {
//                    seller_phone_number.setText("+91-0000000000")
//                } else {
//                    seller_phone_number.setText(mUser_mobile.toString())
//                }
            }
            Keys.GET_CUSTOMER_PROFILE_REQ_CODE -> {
                val get_profile_model = gson.fromJson(response, GetProfileSuccess::class.java)
                val get_profile_list = ArrayList<GetProfileSuccess.Data>()
                get_profile_list.addAll(listOf(get_profile_model.data))
                val mUser_name = get_profile_model?.data!!.user.name
                val mUser_email = get_profile_model.data.user.email
                val mUser_mobile = get_profile_model.data.user.mobile
                Picasso.get().load(get_profile_model.data.user.profile.image).placeholder(R.drawable.down_arrow).into(profile_img)
                profile_name.setText(mUser_name)
                user_email.setText(mUser_email)
                seller_phone_number.setText(mUser_mobile)
//                if (mUser_mobile.equals(null)) {
//                    seller_phone_number.setText("+91-0000000000")
//                } else {
//                    seller_phone_number.setText(mUser_mobile.toString())
//                }

            }
        }

    }

    private fun signOut() {
        auth.signOut()
        googleSignInClient.signOut()
        openA(LoginActivity::class)
        requireActivity().finish()

    }


}