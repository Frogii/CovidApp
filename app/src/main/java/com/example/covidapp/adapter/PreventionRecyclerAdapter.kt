package com.example.covidapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.databinding.PreventionItemBinding
import com.example.covidapp.utils.Prevention

class PreventionRecyclerAdapter() : RecyclerView.Adapter<PreventionRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: PreventionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val list = Prevention.preventionList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PreventionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewPreventionType.text = list[position].typeOfPrevention
            textViewPreventionText.text = list[position].text
            imageViewPrevention.setImageDrawable(ResourcesCompat
                .getDrawable(root.resources, list[position].drawableId, null))
        }
    }
}