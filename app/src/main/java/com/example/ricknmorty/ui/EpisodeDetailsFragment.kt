package com.example.ricknmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ricknmorty.R
import com.example.ricknmorty.api.RetrofitInstance
import kotlinx.coroutines.launch

private lateinit var EpisodeName: TextView
private lateinit var Episode: TextView
private lateinit var Release_Date: TextView
private lateinit var Characters: TextView
private lateinit var backButton: ImageView
class EpisodeDetailsFragment : Fragment() {
    private val args :EpisodeDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_episode_details, container, false)
        val id = args.id

        fun setViews(){
            EpisodeName = view.findViewById(R.id.EpisodeName)
            Episode = view.findViewById(R.id.Episode)
            Release_Date = view.findViewById(R.id.Release_Date)
            Characters = view.findViewById(R.id.Characters)
            backButton = view.findViewById(R.id.back)
        }

        setViews()
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_episodeDetailsFragment_to_episodeFragment2)
        }

        fun setData(){
            lifecycleScope.launch {
                val response = RetrofitInstance.create().getSingleEpisode(id)
                if (response.isSuccessful){
                    response.body().let { response ->
                        EpisodeName.append(response?.name)
                        Episode.append(response?.episode)
                        Release_Date.append(response?.air_date)
                        Characters.append(response?.characters?.size.toString())
                    }
                }
            }
        }
        setData()
        return view
    }
}