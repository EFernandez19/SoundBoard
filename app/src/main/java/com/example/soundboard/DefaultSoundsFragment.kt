package com.example.soundboard

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.soundboard.databinding.ActivityMainBinding
import com.example.soundboard.databinding.DefaultSoundsLayoutBinding

class DefaultSoundsFragment : Fragment() {
    private lateinit var defSoundBinding: DefaultSoundsLayoutBinding
    private var soundpool: SoundPool? = null
    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        defSoundBinding = DefaultSoundsLayoutBinding.inflate(layoutInflater, container, false)

        defSoundBinding.buttonSound1.setOnClickListener{buttonClicked(defSoundBinding.buttonSound1.id)}
        defSoundBinding.buttonSound2.setOnClickListener{buttonClicked(defSoundBinding.buttonSound2.id)}
        defSoundBinding.buttonSound3.setOnClickListener{buttonClicked(defSoundBinding.buttonSound3.id)}

        //audio
        val audioattributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundpool = SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioattributes).build()

        sound1 = soundpool?.load(activity, R.raw.bellsound, 1) ?:0
        sound2 = soundpool?.load(activity, R.raw.applausesound, 1) ?:0
        sound3 = soundpool?.load(activity, R.raw.airhornsound, 1) ?:0

         return defSoundBinding.root
    }
    
    private fun buttonClicked(buttonId: Int)
    {
        val soundId = when (buttonId) {
            R.id.button_sound1 -> sound1
            R.id.button_sound2 -> sound2
            R.id.button_sound3 -> sound3
            else -> 0
        }
        soundpool?.play(soundId, 1F, 1F, 0, 0, 1F)
    }

    override fun onDestroy() {
        super.onDestroy()
        soundpool = null
    }
}