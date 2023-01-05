package com.example.mahsimecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mahsimecommerce.data.User
import com.example.mahsimecommerce.repository.RoomRepository
import com.example.mahsimecommerce.viewmodel.RoomViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.badge_text.view.*
import kotlinx.android.synthetic.main.fragment_inner.view.*


class InnerFragment : Fragment() {

    private lateinit var notificationBadges: View
    private var count: Int = 1

    private lateinit var mUserViewModel: RoomViewModel

    private val args by navArgs<InnerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inner, container, false)


        mUserViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        view.title_product.setText(args.currentUser.title)
        view.price.setText(args.currentUser.price.toString())
        Picasso.get().load(args.currentUser.url)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(view.imageViewInner)

        view.buy_btn.setOnClickListener {
//            updateBadgeCount(count++)
            (activity as MainActivity?)?.updateBadgeCount(count++)

            val user = User (0,args.currentUser.title,args.currentUser.price.toString(),args.currentUser.url)
            mUserViewModel.addUser(user)
        }

        return view
    }
//    private fun updateBadgeCount(count: Int = 0){
//        val itemView: BottomNavigationView? = bottom_navigation.getChildAt(1)as? BottomNavigationView
//
//        notificationBadges = LayoutInflater.from(context).inflate(R.layout.badge_text, itemView, true)
//
//        notificationBadges?.notification_badge?.text = count.toString()
//
//        bottom_navigation.addView(notificationBadges)
//    }
}