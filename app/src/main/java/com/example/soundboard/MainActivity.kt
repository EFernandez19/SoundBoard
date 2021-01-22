package com.example.soundboard

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.soundboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var soundpool: SoundPool? = null
    private var sound1=0; private var sound2=0; private var sound3=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSound1.setOnClickListener{buttonPressed(binding.buttonSound1.id)}
        binding.buttonSound2.setOnClickListener{buttonPressed(binding.buttonSound2.id)}
        binding.buttonSound3.setOnClickListener{buttonPressed(binding.buttonSound3.id)}

        val audioattributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundpool = SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioattributes).build()

        sound1 = soundpool?.load(this, R.raw.bellsound, 1) ?:0
        sound2 = soundpool?.load(this, R.raw.applausesound, 1) ?:0
        sound3 = soundpool?.load(this, R.raw.airhornsound, 1) ?:0
    }

    private fun buttonPressed(buttonId:Int){
        val soundId = when(buttonId){
            R.id.button_sound1 -> sound1
            R.id.button_sound2 -> sound2
            R.id.button_sound3 -> sound3
            else -> 0
        }
        soundpool?.play(soundId, 1F, 1F, 0, 0, 1F)
    }

    override fun onDestroy(){
        super.onDestroy()
        soundpool = null
    }
}
