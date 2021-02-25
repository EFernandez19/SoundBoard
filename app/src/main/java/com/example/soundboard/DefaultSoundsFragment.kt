package com.example.soundboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.soundboard.databinding.DefaultSoundsLayoutBinding

class DefaultSoundsFragment : Fragment() {
    private lateinit var defSoundBinding: DefaultSoundsLayoutBinding
    private val soundPoolManager = SoundPoolManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        defSoundBinding = DefaultSoundsLayoutBinding.inflate(layoutInflater, container, false)

        defSoundBinding.buttonSound1.setOnClickListener { buttonClicked(defSoundBinding.buttonSound1.id) }
        defSoundBinding.buttonSound2.setOnClickListener { buttonClicked(defSoundBinding.buttonSound2.id) }
        defSoundBinding.buttonSound3.setOnClickListener { buttonClicked(defSoundBinding.buttonSound3.id) }

        //Sound stuff
        soundPoolManager.buildSoundPool(activity)
        soundPoolManager.addSound(defSoundBinding.buttonSound1.id, R.raw.bellsound)
        soundPoolManager.addSound(defSoundBinding.buttonSound2.id, R.raw.applausesound)
        soundPoolManager.addSound(defSoundBinding.buttonSound3.id, R.raw.airhornsound)

        return defSoundBinding.root
    }

    private fun buttonClicked(buttonId: Int) = soundPoolManager.playSound(buttonId)

    override fun onDestroy() {
        super.onDestroy()
        soundPoolManager.destroy()
    }
}