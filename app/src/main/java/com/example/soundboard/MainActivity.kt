package com.example.soundboard

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.soundboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var soundpool: SoundPool? = null
    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawer = binding.drawerLayout

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()


        binding.incLayout.buttonSound1.setOnClickListener{buttonPressed(binding.incLayout.buttonSound1.id)}
        binding.incLayout.buttonSound2.setOnClickListener{buttonPressed(binding.incLayout.buttonSound2.id)}
        binding.incLayout.buttonSound3.setOnClickListener{buttonPressed(binding.incLayout.buttonSound3.id)}

        val audioattributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundpool = SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioattributes).build()

        sound1 = soundpool?.load(this, R.raw.bellsound, 1) ?:0
        sound2 = soundpool?.load(this, R.raw.applausesound, 1) ?:0
        sound3 = soundpool?.load(this, R.raw.airhornsound, 1) ?:0

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    private fun buttonPressed(buttonId: Int) {
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
