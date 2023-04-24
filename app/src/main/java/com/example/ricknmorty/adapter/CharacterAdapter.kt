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
import com.example.ricknmorty.ui.CharacterFragmentDirections

class CharacterAdapter(private var result_list:List<com.example.ricknmorty.response.charresponse.Result>):RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val CharImg = itemView.findViewById<ImageView>(R.id.CharImg)
        val CharName = itemView.findViewById<TextView>(R.id.CharName)
        val Status = itemView.findViewById<TextView>(R.id.Status)
        val Species = itemView.findViewById<TextView>(R.id.Species)

        fun bindData(id:Int){
            itemView.setOnClickListener {
                val action = CharacterFragmentDirections.actionCharacterFragment2ToCharacterDetailsFragment(id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.char_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return result_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            CharName.setText(result_list[position].name)
            Status.setText("Status: "+result_list[position].status)
            Species.setText("Species: "+result_list[position].species)
            bindData(result_list[position].id)
        }

        Glide.with(holder.itemView)
            .load(result_list[position].image)
            .error(R.drawable.imageplaceholder)
            .placeholder(R.drawable.imageplaceholder)
            .into(holder.CharImg)
    }
}