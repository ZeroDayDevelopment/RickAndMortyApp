package com.example.ricknmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.ricknmorty.R
import com.example.ricknmorty.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CharacterFragment(),"Home")
        adapter.addFragment(CharacterDetailsFragment(),"Details")
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
