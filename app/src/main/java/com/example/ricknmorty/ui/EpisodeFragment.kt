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
import com.example.ricknmorty.adapter.EpisodeAdapter
import com.example.ricknmorty.adapter.LocationAdapter
import com.example.ricknmorty.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private var totalPage = 1
private var currentPage = 1
private  var episode_list = mutableListOf<com.example.ricknmorty.episoderesponse.Result>()
private lateinit var adapter: EpisodeAdapter
private lateinit var recyclerView: RecyclerView
class EpisodeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_episode, container, false)
        currentPage = 1
        episode_list.clear()
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = EpisodeAdapter(episode_list)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            fetchData(currentPage)
        }
        recyclerViewScroll()
        return view
    }

    private fun recyclerViewScroll(){
        recyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy>0){
                    val onScreenitems = recyclerView.layoutManager?.childCount
                    val pastItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val totalItems = recyclerView.layoutManager?.itemCount
                    if (onScreenitems!!+pastItems >= totalItems!!){
                        currentPage++
                        if (currentPage< totalPage){
                            lifecycleScope.launch {
                                fetchData(currentPage)
                            }
                        }
                    }
                }
            }
        })
    }

    private suspend fun fetchData(page:Int){
        val response = RetrofitInstance.create().getAllEpisodes(page)
        withContext(Dispatchers.Main){
            if (response.isSuccessful){
                response.body().let {
                    episode_list.addAll(it!!.results)
                    adapter.notifyDataSetChanged()
                    totalPage = it.info.pages
                }
            }
        }
    }
}