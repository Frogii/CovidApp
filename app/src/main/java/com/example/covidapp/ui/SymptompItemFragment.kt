package com.example.covidapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import com.example.covidapp.R
import com.example.covidapp.databinding.FragmentSymptompItemBinding
import com.example.covidapp.utils.Constants.ARGS_IMAGE
import com.example.covidapp.utils.Constants.ARGS_TEXT

class SymptompItemFragment : Fragment() {

    lateinit var fragmentItemSymptompBinding: FragmentSymptompItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_symptomp_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentItemSymptompBinding = FragmentSymptompItemBinding.bind(view)
        arguments?.let {
            fragmentItemSymptompBinding.apply {
                texViewPagerSymptomp.text = resources.getString(it.getInt(ARGS_TEXT))
                imageViewPagerSymptomp.setImageDrawable(
                    ResourcesCompat
                        .getDrawable(resources, it.getInt(ARGS_IMAGE), null)
                )
            }
        }
    }

    companion object {

        fun newInstance(textId: Int, drawableId: Int) =
            SymptompItemFragment().apply {
                arguments = bundleOf(ARGS_TEXT to textId, ARGS_IMAGE to drawableId)
            }
    }
}