package com.example.covidapp.utils

import com.example.covidapp.R

data class Prevention(val typeOfPrevention: String, val text: String, val drawableId: Int) {

    companion object {
        val preventionList = listOf<Prevention>(
            Prevention("Wear face mask",
            "Since the start of the coronavirus outbreak some places have fully embraced wearing face masks, " +
                "and anyone caught without one risks becoming a social pariah.", R.drawable.image_wear_mask),
            Prevention("Wash your hands",
            "These diseases include gastrointestinal infections such as Salmonella, " +
                "and respiratory infections, such as influenza.", R.drawable.image_wash_hands))
    }
}


