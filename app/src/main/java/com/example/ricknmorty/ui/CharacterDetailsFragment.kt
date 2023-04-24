package com.example.ricknmorty.ui

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ricknmorty.R
import com.example.ricknmorty.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var CharimageView:ImageView
private lateinit var CharName:TextView
private lateinit var Species:TextView
private lateinit var Status:TextView
private lateinit var Gender:TextView
private lateinit var Origin:TextView
private lateinit var Location:TextView
private lateinit var Appears:TextView
private lateinit var backButton:ImageView

class CharacterDetailsFragment : Fragment() {

    val args:CharacterDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_character_details, container, false)
        fun setViews(){
            CharimageView = view.findViewById(R.id.CharimageView)
            CharName = view.findViewById(R.id.CharName)
            Species = view.findViewById(R.id.Species)
            Status = view.findViewById(R.id.Status)
            Gender = view.findViewById(R.id.Gender)
            Origin = view.findViewById(R.id.Origin)
            Location = view.findViewById(R.id.Location)
            Appears = view.findViewById(R.id.Appears)
            backButton = view.findViewById(R.id.back)
        }
        setViews()
        val id = args.id
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_characterDetailsFragment_to_characterFragment2)
        }
        fun setData(){
            lifecycleScope.launch{
                val response = RetrofitInstance.create().getSingleCharacter(id)
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        response.body().let { response ->
                            CharName.append(response?.name)
                            Species.append(response?.species)
                            Status.append(response?.status)
                            Gender.append(response?.gender)
                            Origin.append((response?.origin!!.name))
                            Location.append((response?.location?.name))
                            Appears.append(response?.episode?.size.toString()+" Episode\n")

                            Glide.with(requireView())
                                .load(response.image)
                                .error(R.drawable.imageplaceholder)
                                .into(CharimageView)
                        }

                    }
                }
            }
        }
        setData()

        return view
    }


}