package com.example.mahsimecommerce.fragments.Profile.Forget

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
import com.example.mahsimecommerce.fragments.Profile.SignUp.SignUpFragment
import com.google.android.material.snackbar.Snackbar
import com.parse.ParseUser
import com.parse.SignUpCallback
import kotlinx.android.synthetic.main.dialog_progress.*
import kotlinx.android.synthetic.main.fragment_forget.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class ForgetFragment : Fragment() {
    private lateinit var mProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        val view = inflater.inflate(R.layout.fragment_forget, container, false)

        view.btn_submit.setOnClickListener {
            showProgressDialog("لطفا صبر کنید")
            val email: String = et_email.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            } else {
                // This piece of code is used to send the reset password link to the user's email id if the user is registered.

                ParseUser.requestPasswordResetInBackground(
                    email
                ) { e ->
                    if (e == null) {
                        showAlert("ایمیل با موفقیت ارسال شد", " لطفا ایمیل خود را چک کنید", false)
                        val profileFragment = ProfileFragment()
                        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                        transaction?.replace(R.id.fragmentProfile, profileFragment)
                        transaction?.commit()
                    } else {
                        showAlert("با مشکل مواجه شد", e?.message + " لطفا مجدد تلاش کنید", true)
                    }
                }
            }
            hideProgressDialog()
        }

        return view
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
}