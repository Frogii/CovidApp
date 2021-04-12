package com.example.covidapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.databinding.CountryItemBinding
import com.example.covidapp.model.CountryItem

class CountriesRecAdapter() : RecyclerView.Adapter<CountriesRecAdapter.CountriesViewHolder>() {

    private var countries = listOf<CountryItem>()

    fun setList(list: List<CountryItem>) {
        countries = list.sortedBy { it.name }
        notifyDataSetChanged()
    }

    class CountriesViewHolder(val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        return CountriesViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.binding.textViewCountryName.text = countries[position].name
        holder.binding.textViewShortName.text = countries[position].shortCountryName
    }
}
