package com.example.ricknmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ricknmorty.R
import com.example.ricknmorty.ui.EpisodeFragmentDirections

class EpisodeAdapter(private var episodelist:List<com.example.ricknmorty.episoderesponse.Result>):RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val EpisodeName = itemView.findViewById<TextView>(R.id.CharName)
        val Episode = itemView.findViewById<TextView>(R.id.Status)
        val Characters = itemView.findViewById<TextView>(R.id.Species)
        fun bindData(id:Int){
            itemView.setOnClickListener {
                val action = EpisodeFragmentDirections.actionEpisodeFragment2ToEpisodeDetailsFragment(id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.char_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = episodelist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            EpisodeName.setText(episodelist[position].name)
            Episode.setText("Episode: "+episodelist[position].episode)
            Characters.setText("Character Number: "+episodelist[position].characters.size)
            bindData(episodelist[position].id)
        }
    }
}