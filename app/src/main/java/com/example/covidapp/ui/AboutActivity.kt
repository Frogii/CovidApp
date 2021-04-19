package com.example.covidapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
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


    }

    fun setupRecycler() {
        preventionRecyclerAdapter = PreventionRecyclerAdapter()
        aboutBinding.recyclerPreventions.adapter = preventionRecyclerAdapter
        aboutBinding.recyclerPreventions.layoutManager = LinearLayoutManager(aboutBinding.root.context)
    }
}