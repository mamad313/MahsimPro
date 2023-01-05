package com.example.mahsimecommerce.fragments.Profile

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.fragments.Profile.Forget.ForgetFragment
import com.example.mahsimecommerce.fragments.Profile.SignUp.SignUpFragment
import com.example.mahsimecommerce.helper.Setting
import com.google.android.material.snackbar.Snackbar
import com.parse.ParseException
import com.parse.ParseUser
import kotlinx.android.synthetic.main.dialog_progress.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

private lateinit var mProgressDialog: Dialog

class ProfileFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        var setting = Setting(requireContext())
        if(setting.getLoginInfo()!=null){
            val profileInfo = ProfileInfo()
            val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentProfile, profileInfo)
            transaction?.commit()
        }

        view.tv_forgot_password.setOnClickListener(this)
        view.btn_login.setOnClickListener(this)
        view.tv_register.setOnClickListener(this)


        return view


    }



    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(username.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }


            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            else -> {
                true
            }
        }
    }

    private fun showAlert(title: String, message: String, error: Boolean) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
                // don't forget to change the line below with the names of your Activities
                if (!error) {
                    val intent = Intent(context , ProfileFragment::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        val ok = builder.create()
        ok.show()
    }

    private fun loginFun(){
        val user: String = view?.username?.text.toString().trim() { it <= ' ' }
        val password: String = view?.et_password?.text.toString().trim { it <= ' ' }

        if(validateRegisterDetails()) {
            showProgressDialog("لطفا منتظر بمانید")
            ParseUser.logInInBackground(user, password) { parseUser: ParseUser?, e: ParseException? ->
                if (parseUser != null) {
                    var settings = Setting(requireContext())
                    ParseUser.logOut()
                    settings.saveLogin(parseUser.objectId, parseUser.username, parseUser.email)
                    showAlert("ورود موفقیت آمیز", " خوش آمیدید!$user", false)
                    val profileInfo = ProfileInfo()
                    val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentProfile, profileInfo)
                    transaction?.commit()
                } else {
                    ParseUser.logOut()
                    showAlert("ورود نا موفق", e?.message + " لطفا مجدد تلاش کنید", true)
                }
                hideProgressDialog()
            }
        }
    }
    override fun onClick(v: View?) {
        if (v != null){
            when (v.id){
                R.id.tv_forgot_password -> {
                    val forgetFragment = ForgetFragment()
                    val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentProfile, forgetFragment)
                    transaction?.commit()

                }
                R.id.btn_login -> {
                    loginFun()
                    Log.d("click","clicked")
                }

                R.id.tv_register -> {
                    val signUpFragment = SignUpFragment()
                    val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentProfile, signUpFragment)
                    transaction?.commit()
                }
            }
        }
    }
    fun showErrorSnackBar(message: String, errorMessage: Boolean) {

        val snackBar =
            view?.let { Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG) }
        val snackBarView = snackBar?.view

        if (errorMessage) {
            if (snackBarView != null) {
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
            }
        }else{
            if (snackBarView != null) {
                snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
            }
        }
        if (snackBar != null) {
            snackBar.show()
        }
    }

    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(requireContext())

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.tv_progress_text.text = text

        //Start the dialog and display it on screen.
        mProgressDialog.show()
    }
    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }

}