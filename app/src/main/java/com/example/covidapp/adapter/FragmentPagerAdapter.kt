package com.example.covidapp.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.covidapp.ui.SymptompItemFragment
import com.example.covidapp.utils.Symptomp

class FragmentPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = SymptompItemFragment.newInstance(
        Symptomp.symptompList[position].textId,
        Symptomp.symptompList[position].drawableId
    )

    override fun getCount() = Symptomp.symptompList.size

}