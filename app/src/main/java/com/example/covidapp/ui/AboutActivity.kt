package com.example.covidapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.adapter.FragmentPagerAdapter
import com.example.covidapp.adapter.PreventionRecyclerAdapter
import com.example.covidapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var aboutBinding: ActivityAboutBinding
    lateinit var preventionRecyclerAdapter: PreventionRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)

        setupRecycler()

        aboutBinding.imageViewMenuAbout.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        val adapter = FragmentPagerAdapter(supportFragmentManager)
        aboutBinding.viewPagerSymptomp.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }
    private fun setupRecycler() {
        preventionRecyclerAdapter = PreventionRecyclerAdapter()
        aboutBinding.recyclerPreventions.adapter = preventionRecyclerAdapter
        aboutBinding.recyclerPreventions.layoutManager = LinearLayoutManager(aboutBinding.root.context)
    }
}