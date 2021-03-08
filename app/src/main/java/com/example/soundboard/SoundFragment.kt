package com.example.soundboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.example.soundboard.databinding.FragmentSoundBinding

class SoundFragment(private val fragName: String) : Fragment() {
    private lateinit var soundFragBinding: FragmentSoundBinding
    private lateinit var soundPoolManager: SoundPoolManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        soundFragBinding = FragmentSoundBinding.inflate(layoutInflater, container, false)

        //write fragName at top of fragment
        soundFragBinding.textviewName.text = fragName

        val linlay: LinearLayoutCompat = soundFragBinding.linearlayoutBellFragment

        soundPoolManager = SoundPoolManager(activity)
        soundPoolManager.buildSoundPool()

        addButtons(activity, linlay)

        return soundFragBinding.root
    }

    //dynamically create buttons based on how many sound files there are
    private fun addButtons(context: Context?, container: ViewGroup) {
        val directoryPath = "raw/$fragName"
        val assetsScanner = AssetsScanner(context)
        val buttonNames = assetsScanner.listFiles(directoryPath)

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

            val buttonPath = "$directoryPath/$buttonName"

            //Try to load sound from assets
            try {
                soundPoolManager.addSound(generatedButton.id, assetsScanner.openFd(buttonPath))
            } catch (e: Exception) {
                continue
            } //if not found do nothing


            container.addView(generatedButton)
        }
    }

    private fun buttonPressed(buttonId: Int) {
        soundPoolManager.playSound(buttonId)
    }
}