package com.example.ricknmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ricknmorty.R
import com.example.ricknmorty.api.RetrofitInstance
import kotlinx.coroutines.launch

private lateinit var LocationName:TextView
private lateinit var Type:TextView
private lateinit var Dimension:TextView
private lateinit var Residents:TextView
private lateinit var backButton: ImageView


class LocationDetailsFragment : Fragment() {
    private val args:LocationDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_details, container, false)
        val id = args.id

        fun setViews(){
            LocationName = view.findViewById(R.id.LocationName)
            Type = view.findViewById(R.id.Type)
            Dimension = view.findViewById(R.id.Dimension)
            Residents = view.findViewById(R.id.Residents)
            backButton = view.findViewById(R.id.LocationBack)
        }
        setViews()
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_locationDetailsFragment_to_locationFragment2)
        }

        fun setData(){
            lifecycleScope.launch {
                val response = RetrofitInstance.create().getSingleLocation(id)
                if (response.isSuccessful){
                    response.body().let { response ->
                        LocationName.append(response?.name)
                        Type.append(response?.type)
                        Dimension.append(response?.dimension)
                        Residents.append(response?.residents?.size.toString())
                    }
                }
            }
        }
        setData()
        return view
    }

}