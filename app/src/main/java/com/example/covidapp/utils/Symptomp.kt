package com.example.covidapp.utils

import com.example.covidapp.R

data class Symptomp(val textId: Int, val drawableId: Int) {

    companion object {
        val symptompList = listOf<Symptomp>(
            Symptomp(
                R.string.headache,
                R.drawable.image_headache
            ),
            Symptomp(
                R.string.cough,
                R.drawable.image_caugh
            ),
            Symptomp(
                R.string.fever,
                R.drawable.image_fever
            )
        )
    }
}