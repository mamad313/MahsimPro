package com.example.mahsimecommerce.fragments.Home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mahsimecommerce.R
import com.example.mahsimecommerce.models.ChildSlider
import com.squareup.picasso.Picasso


class ViewPagerAdapter(private var SliderList: List<ChildSlider>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            imageView.setOnClickListener { v: View ->
                val position = adapterPosition
                Toast.makeText(itemView.context," You Clicked ON Item #${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {

        holder.textView.text = SliderList[holder.adapterPosition].title
        Log.d("onBindViewHolder","onBindViewHolder -> ${SliderList[0].url}")

        Picasso.get().load(SliderList[holder.adapterPosition].url)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return SliderList.size
    }

}