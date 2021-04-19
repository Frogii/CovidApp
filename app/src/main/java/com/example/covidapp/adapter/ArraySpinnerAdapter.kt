package com.example.covidapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.covidapp.databinding.SpinnerDropdownItemBinding
import com.example.covidapp.databinding.SpinnerItemBinding

class ArraySpinnerAdapter(context: Context, resource: Int, list: List<String>) :
    ArrayAdapter<String>(context, resource, list) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var binding: SpinnerItemBinding

    private var listOnCountries: List<String> = ArrayList()

    fun setList(list: List<String>) {
        listOnCountries = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            binding = SpinnerItemBinding.inflate(layoutInflater, parent, false)
        }
        var country = getItem(position)
        if (listOnCountries.isNotEmpty()) {
            country = listOnCountries[position]
        }
        binding.textViewSpinner.text = country
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SpinnerDropdownItemBinding.inflate(layoutInflater, parent, false)
        var country = getItem(position)
        if (listOnCountries.isNotEmpty()) {
            country = listOnCountries[position]
        }
        binding.textViewDropdownSpinner.text = country
        return binding.root
    }
}