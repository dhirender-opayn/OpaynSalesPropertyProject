package com.example.opaynpropertyproject.login_signup_activity


import android.content.Intent
import android.graphics.ColorSpace
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.example.opaynpropertyproject.customer.CustomerHomeActivity
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.ApiResponse
import com.example.opaynpropertyproject.api.Keys
import com.example.opaynpropertyproject.api.Keys.TOKEN
import com.example.opaynpropertyproject.api.Keys.USERDATA
import com.example.opaynpropertyproject.api.Keys.USERID
import com.example.opaynpropertyproject.api.Keys.USER_MOBILE
import com.example.opaynpropertyproject.api_model.ErrorModel
import com.example.opaynpropertyproject.api_model.LoginSuccessModel
import com.example.opaynpropertyproject.comman.BaseActivity
import com.example.opaynpropertyproject.comman.SharedPreferenceManager
import com.example.opaynpropertyproject.comman.Utils
import com.example.opaynpropertyproject.comman.Utils.customSnakebar
import com.example.opaynpropertyproject.home_activity.HomeActivity
import com.example.opaynpropertyproject.home_activity.HomeActivity.Companion.token
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

import com.google.android.gms.auth.api.signin.GoogleSignInResult

import com.google.android.gms.auth.api.Auth
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_account_setting.*


class LoginActivity : BaseActivity(), View.OnClickListener, ApiResponse {
    lateinit var loginHashMap: HashMap<String, Any>


    private lateinit var googleSignInClient: GoogleSignInClient

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        loginHashMap = HashMap<String, Any>()


        getuserdata()
        login_container.setOnClickListener(this)
        create_account_btn_login.setOnClickListener(this)
        forget_password.setOnClickListener(this)
        login_button.setOnClickListener(this)
        google_gmail_icon.setOnClickListener(this)


        //================//

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("771958017542-bu8apihnvm0oim64sfd6v3p475d2nr6k.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]

    }

    private fun loginUserValue(): Boolean {
        val mLogin_email = login_email.text.toString().trim()
        if (mLogin_email.isEmpty()) {
            Utils.customSnakebar(login_email, getString(R.string.email_required))
            return false
        }
        if (!Utils.isValidEmailId(mLogin_email)) {
            Utils.customSnakebar(login_email, getString(R.string.email_pattern))
            return false
        }
        if (login_password.text.toString().trim().isEmpty()) {
            Utils.customSnakebar(login_password, getString(R.string.password_required))
            return false
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login_button -> {
                Log.e("button", "click")
                if (loginUserValue()) {
                    loginHashMap.put(Keys.login_email, login_email.text.toString().trim())
                    loginHashMap.put(Keys.login_password, login_password.text.toString().trim())
                    Log.e("email", login_email.text.toString().trim())
                    Log.e("password", login_password.text.toString().trim())

                    serviceViewModel.postservice(
                        Keys.loginEndPoint,
                        this,
                        loginHashMap,
                        Keys.login_log,
                        false,
                        "",
                        true,
                        this
                    )
                }
            }
            R.id.create_account_btn_login -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.forget_password -> {
                val intent = Intent(this, ForgetPasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.login_container -> {
                Utils.hideKeyboard(this)
            }
            R.id.google_gmail_icon -> {
                val intent = googleSignInClient.signInIntent
                startActivityForResult(intent, RC_SIGN_IN)
            }
        }
    }

    //check if user already login or Not (Automatic Go HomeScreen) //
    private fun getuserdata() {
        Handler(getMainLooper()).postDelayed({
            SharedPreferenceManager(this).getString(Keys.USERID).let {
                if (it == null || it.toString().equals("")) {
                    Toast.makeText(this,getString(R.string.username_password),Toast.LENGTH_LONG).toString()

                } else {

                    val mGon = SharedPreferenceManager(this).getString(Keys.USERDATA)
                    val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
                    if (model.data.user.roles[0].name.equals("Customer")) {
                        token = model.data.token
                        Keys.isCustomer = true
                        openA(CustomerHomeActivity::class)
                        finishAffinity()
                    } else {
                        Keys.isCustomer = false
                        token = model.data.token
                        openA(HomeActivity::class)
                        finishAffinity()
                    }
                }
            }

        }, 1000)

    }
    private fun setData() {
        val mGon = SharedPreferenceManager(this).getString(Keys.USERDATA)
        val model = gson.fromJson(mGon, LoginSuccessModel::class.java)
        profile_setting_user_name.setText(model.data.user.name)
        profile_setting_email.setText(model.data.user.email)
        profile_setting_phone.setText(model.data.user.mobile.toString())
        Picasso.get().load(model.data.user.profile.image).placeholder(R.drawable.down_arrow)
            .into(account_setting_img_holder)


    }

    override fun onResponse(requestcode: Int, response: String) {
        Log.e("reposneeeeee", response);
        when (requestcode) {


            Keys.login_log -> {
                val model = gson.fromJson(response, LoginSuccessModel::class.java)
                val user_type = model.data.user.roles[0].name
                if (user_type.equals(Keys.CUSTOMER)) {
                    if (model?.data != null) {
                        SharedPreferenceManager(this).saveString(TOKEN, model.data.token)
                        SharedPreferenceManager(this).saveString(USERDATA, response)
                        SharedPreferenceManager(this).saveString(
                            USERID,
                            model.data.user.id.toString()
                        )
                        token = model.data.token
                        openA(CustomerHomeActivity::class)
                        finishAffinity()
                        Keys.isCustomer = true

                    }
                } else {
                    if (model?.data != null) {
                        SharedPreferenceManager(this).saveString(TOKEN, model.data.token)
                        SharedPreferenceManager(this).saveString(
                            USER_MOBILE,
                            model.data.user.mobile.toString()
                        )
                        SharedPreferenceManager(this).saveString(
                            USERID,
                            model.data.user.id.toString()
                        )
                        SharedPreferenceManager(this).saveString(USERDATA, response)

                        token = model.data.token
                        openA(HomeActivity::class)
                        finishAffinity()

                    }
                }

            }

            Keys.BACKENDERROR -> {
                val errorModel = gson.fromJson(response, ErrorModel::class.java)
                customSnakebar(login_button, errorModel.message.toString())
                Log.e("eeee", response.toString())
            }
        }

    }


    //====================================================//

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }


    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.e("erererrerere", "111111")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val result: GoogleSignInResult =
                    Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                handleSignInResult(result)

//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
//                val idToken = account.idToken
//                val name = account.displayName
//                val email = account.email
//
//                val credential = GoogleAuthProvider.getCredential(idToken, null)
//                firebaseAuthWithGoogle(credential.toString())

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        } else {
            Log.e("erererrerere", "222222222")

        }
    }

//    // [START auth_with_google]
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    updateUI(null)
//                }
//            }
//    }

    private fun updateUI(user: FirebaseUser?) {
        if (user !== null) {

            val bundle = Bundle()
            bundle.putString(Keys.USER_NAME, user.displayName.toString())
            bundle.putString(Keys.USERID, user.uid)
            bundle.putString(Keys.USER_EMAIL, user.email)
            bundle.putString(Keys.USERMOBILE, user.phoneNumber)
            openA(HomeActivity::class, bundle)
            finish()
        }


    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            Keys.isGoogleAccount = true

            val account = result.signInAccount
            Log.e("tokiiddddd", account.id)

            Log.e("useridgoogle", account.id.toString())
            Log.e("tokengoogle", account.idToken.toString())
            Log.e("userDatagoogle", account.account.toString())


            SharedPreferenceManager(this).saveNumber(Keys.USERID, account.id.toLong())
            openA(SellerBuyerProfileSetActivity::class)
            finish()
//            idToken = account.idToken
//            name = account.displayName
//            email = account.email
//            // you can store user data to SharedPreference
//            val credential = GoogleAuthProvider.getCredential(idToken, null)
//            firebaseAuthWithGoogle(credential)
        } else {
            // Google Sign In failed, update UI appropriately
            Log.e("ohhhhhhhhh", result.status.toString())
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }


}