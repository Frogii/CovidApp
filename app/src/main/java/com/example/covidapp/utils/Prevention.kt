package com.example.covidapp.utils

import com.example.covidapp.R

data class Prevention(val typeOfPrevention: Int, val text: Int, val drawableId: Int) {

    companion object {
        val preventionList = listOf<Prevention>(
            Prevention(
                R.string.wear_face_mask,
                R.string.wear_mask_text, R.drawable.image_wear_mask
            ),
            Prevention(
                R.string.wash_your_hands,
                R.string.wash_your_text, R.drawable.image_wash_hands
            )
        )
    }
}


