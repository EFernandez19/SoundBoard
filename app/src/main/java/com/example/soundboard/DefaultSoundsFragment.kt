package com.example.soundboard

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.soundboard.databinding.ActivityMainBinding
import com.example.soundboard.databinding.DefaultSoundsLayoutBinding

class DefaultSoundsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.default_sounds_layout, container, false)
    }
}