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
        val directory = "raw/Bell"
        val assetsScanner = AssetsScanner(context)

        val buttonNames = assetsScanner.listFiles(directory)

        for (buttonName in buttonNames) {
            //create button
            val generatedButton = layoutInflater.inflate(R.layout.button, null) as Button
            val buttonText = buttonName.substring(0, buttonName.lastIndexOf("."))
            generatedButton.text = buttonText
            generatedButton.id = buttonText.hashCode()
            generatedButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            generatedButton.setOnClickListener { buttonPressed(generatedButton.id) }

            val buttonPath = "$directory/$buttonName"

            //Try to load sound from assets
            try {
                soundPoolManager.addSound(generatedButton.id, assetsScanner.openFd(buttonPath))
            }
            catch(e: Exception){} //if not found do nothing

            
            container.addView(generatedButton)
        }
    }

    private fun buttonPressed(buttonId: Int) {
        soundPoolManager.playSound(buttonId)
    }
}