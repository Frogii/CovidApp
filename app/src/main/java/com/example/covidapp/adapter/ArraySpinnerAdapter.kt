package com.example.covidapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.covidapp.databinding.SpinnerDropdownItemBinding
import com.example.covidapp.databinding.SpinnerItemBinding
import com.example.covidapp.model.CountryItem

class ArraySpinnerAdapter(context: Context, list: List<CountryItem>) :
    ArrayAdapter<CountryItem>(context, 0, list) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var binding: SpinnerItemBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            binding = SpinnerItemBinding.inflate(layoutInflater, parent, false)
        }
        binding.textViewSpinner.text = getItem(position)?.name
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SpinnerDropdownItemBinding.inflate(layoutInflater, parent, false)
        binding.textViewDropdownSpinner.text = getItem(position)?.name
        return binding.root
    }
}