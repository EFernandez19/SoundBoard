package com.example.soundboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.soundboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSound1.setOnClickListener{buttonPressed(binding.buttonSound1.text.toString())}
        binding.buttonSound2.setOnClickListener{buttonPressed(binding.buttonSound2.text.toString())}
        binding.buttonSound3.setOnClickListener{buttonPressed(binding.buttonSound3.text.toString())}

    }

    fun buttonPressed(buttonId:String){
        val toast = Toast.makeText(this, buttonId, Toast.LENGTH_SHORT)
        toast.show()
    }
}