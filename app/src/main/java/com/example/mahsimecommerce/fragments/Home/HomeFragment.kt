package com.example.mahsimecommerce.fragments.Home

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.viewmodel.ViewModelMain
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var firstViewUserModel: ViewModelMain
    private lateinit var secondViewUserModel: ViewModelMain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager = view?.view_pager2
        firstViewUserModel = ViewModelProvider(this)[ViewModelMain::class.java]
//        viewPager?.adapter = adapter
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        firstViewUserModel.liveDataVar.observe(viewLifecycleOwner, Observer { user ->
            viewPager?.adapter = ViewPagerAdapter(user)
            view.indicator.setViewPager(view.view_pager2)
            Log.d("firstViewUserModel","firstViewUserModel -> $user")
        })

        val recyclerView = view?.recycler_view
        secondViewUserModel = ViewModelProvider(this)[ViewModelMain::class.java]
        recyclerView?.layoutManager = GridLayoutManager(view.context, 2)

        secondViewUserModel.liveDataVar2.observe(viewLifecycleOwner, Observer { user ->
            recyclerView?.adapter = ProductAdapter(user)
            Log.d("firstViewUserModel","firstViewUserModel -> $user")
        })



        return view
    }

}