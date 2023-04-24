package com.example.ricknmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ricknmorty.R
import com.example.ricknmorty.ui.LocationFragmentDirections

class LocationAdapter(private var location_list:List<com.example.ricknmorty.locationresponse.Result>):RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val LocationName = itemView.findViewById<TextView>(R.id.CharName)
        val LocationType = itemView.findViewById<TextView>(R.id.Status)
        val LocationDimension = itemView.findViewById<TextView>(R.id.Species)
        fun bindData(id:Int){
            itemView.setOnClickListener {
                val action = LocationFragmentDirections.actionLocationFragment2ToLocationDetailsFragment(id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.char_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = location_list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            LocationName.setText(location_list[position].name)
            LocationDimension.setText("Dimension: "+location_list[position].dimension)
            LocationType.setText("Type: "+location_list[position].type.toString())
            bindData(location_list[position].id)
        }
    }
}