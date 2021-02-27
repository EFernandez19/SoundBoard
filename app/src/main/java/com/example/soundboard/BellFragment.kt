package com.example.soundboard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.example.soundboard.databinding.FragmentBellBinding

class BellFragment : Fragment() {
    private lateinit var bellFragBinding: FragmentBellBinding
    private lateinit var soundPoolManager:SoundPoolManager

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bellFragBinding = FragmentBellBinding.inflate(layoutInflater, container, false)

        val linlay: LinearLayoutCompat = bellFragBinding.linearlayoutBellFragment
        
        soundPoolManager = SoundPoolManager(activity)
        soundPoolManager.buildSoundPool()
        
        addButtons(activity, linlay)

        return bellFragBinding.root
    }

    //dynamically create buttons based on how many sound files there are
    private fun addButtons(context: Context?, container: ViewGroup) {
        val assetsScanner = AssetsScanner(context)

        val buttonNames = assetsScanner.listFiles("raw/Bell")

        for (buttonName in buttonNames) {
            //create button
            val generatedButton = layoutInflater.inflate(R.layout.button, null) as Button
            generatedButton.text = buttonName.substring(0, buttonName.lastIndexOf("."))
            generatedButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            generatedButton.setOnClickListener { generatedButton.id }

            // TODO: 2/26/2021 Load sound from assets 
            
            container.addView(generatedButton)
        }
    }

    fun buttonPressed(buttonId: Int) {
        // TODO: 2/26/2021 play sound when button pressed
    }
}