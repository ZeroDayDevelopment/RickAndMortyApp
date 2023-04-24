package com.example.ricknmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ricknmorty.R
import com.example.ricknmorty.adapter.CharacterAdapter
import com.example.ricknmorty.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private var currentPage = 1
private var totalPage = 1
private lateinit var adapter: CharacterAdapter
private lateinit var recyclerView: RecyclerView
private val characterList = mutableListOf<com.example.ricknmorty.response.charresponse.Result>()
class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)
        characterList.clear()
        currentPage = 1
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = CharacterAdapter(characterList)
        recyclerView.adapter = adapter
        lifecycleScope.launch{
            fetchData(currentPage)
        }

        recyclerViewScroll()
        return view
    }


    private fun recyclerViewScroll(){
        recyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy!=0){
                        val onScreenItem = recyclerView.layoutManager?.childCount
                        val pastItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        val totalItems = recyclerView.layoutManager?.itemCount
                    if (onScreenItem!! + pastItems >= totalItems!!){
                        currentPage++
                        if (currentPage < totalPage){
                            lifecycleScope.launch{
                                fetchData(currentPage)
                            }
                        }
                    }
                }
            }
        })
    }

    private suspend fun fetchData(page:Int){
            val response = RetrofitInstance.create().getAllCharacters(page)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body().let {
                        characterList.addAll(it!!.results)
                        adapter.notifyDataSetChanged()
                        totalPage = it.info.pages
                    }
                }
            }
        }
}