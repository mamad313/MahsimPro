package com.example.mahsimecommerce.fragments.Profile.SignUp

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
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.fragments.Profile.ProfileFragment
import com.google.android.material.snackbar.Snackbar
import com.parse.ParseUser
import com.parse.SignUpCallback
import kotlinx.android.synthetic.main.dialog_progress.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.et_password
import kotlinx.android.synthetic.main.fragment_profile.username
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

private lateinit var mProgressDialog: Dialog

class SignUpFragment : Fragment(), View.OnClickListener {

    lateinit var buttonVal: Button
    lateinit var textVal: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        buttonVal=view.findViewById(R.id.btn_register)
        textVal=view.findViewById(R.id.tv_login)

        buttonVal.setOnClickListener(this)
        textVal.setOnClickListener(this)

        return view
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

    private fun registerUser(){
        val username: String = username.text.toString().trim { it <= ' ' }
//            val lastName: String = et_last_name.text.toString().trim { it <= ' ' }
        val email: String = et_email.text.toString().trim { it <= ' ' }
        val password: String = et_password.text.toString().trim { it <= ' ' }

        if(validateRegisterDetails()) {
            showProgressDialog("لطفا منتظر بمانید")
            val user = ParseUser()
            user.username = username
            user.setPassword(password)
            user.email = email
            user.signUpInBackground(SignUpCallback {
                if (it == null) {
                    ParseUser.logOut();
                    showAlert("اکانت با موفقیت ساخته شد", "یه مهسیم خوش آمدید", false)
                    val profileFragment = ProfileFragment()
                    val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentProfile, profileFragment)
                    transaction?.commit()
                } else {
                    ParseUser.logOut();
                    showAlert("ساخت اکانت به با مشکل روبرو شد", "اکانت نمیتواند ساخته شود به دلیل" + " :" + it.message, true)
                }
                hideProgressDialog()
            })
        }
    }
    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(username.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(et_last_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(et_confirm_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            et_password.text.toString().trim { it <= ' ' } != et_confirm_password.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
            }
            else -> {
                showErrorSnackBar("اطلاعات با موفقیت ذخیره شد", false)
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
    override fun onClick(v: View?) {
        if (v != null){
            when (v.id){
                R.id.tv_login -> {
                    val profileFragment = ProfileFragment()
                    val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.fragmentProfile, profileFragment)
                    transaction?.commit()

                }
                R.id.btn_register -> {
                    Log.d("click","clicked")
                    registerUser()
                }

            }
        }
    }
}