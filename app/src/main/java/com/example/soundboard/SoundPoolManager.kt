package com.example.soundboard

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.SoundPool

/*
SoundPoolManager
will create/destroy soundpool
load/play/stop sounds
 */
class SoundPoolManager(
    private var context: Context?,
    private var soundPool: SoundPool? = null,
    private var audioAttributes: AudioAttributes? = null,
    private val soundMap: HashMap<Int, Int> = HashMap()
) {

    //instantiate the SoundPool
    fun buildSoundPool(
        soundPool: SoundPool? = null,
        audioAttributes: AudioAttributes? = null,
        maxstreams: Int = 5
    ) {
        //build audioAttributes
        this.audioAttributes = audioAttributes ?: AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        //build soundPool
        this.soundPool = soundPool ?: SoundPool.Builder().setMaxStreams(maxstreams)
            .setAudioAttributes(this.audioAttributes)
            .build()
    }


    //adds sounds to soundMap
    fun addSound(soundId: Int, sound: Int) {
        soundMap[soundId] = soundPool?.load(context, sound, 1) ?: 0
    }

    fun addSound(soundId: Int, assetFileDescriptor: AssetFileDescriptor?) {
        soundMap[soundId] = soundPool?.load(assetFileDescriptor, 1) ?: 0
    }

    fun playSound(soundId: Int) {
        soundMap[soundId]?.let { soundPool?.play(it, 1F, 1F, 0, 0, 1F) }
    }

    //destroy soundPool
    fun destroy() {
        soundPool = null
        audioAttributes = null
    }
}