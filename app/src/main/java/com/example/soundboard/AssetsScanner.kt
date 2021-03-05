package com.example.soundboard

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager

class AssetsScanner(
    context: Context?,
    private var assetManager: AssetManager? = null
) {

    init {
        assetManager = context?.resources?.assets
    }

    //list directories and files
    fun listAll(path: String): Array<String>? = assetManager?.list(path)

    //List all nonempty directories in path, non-recursive
    fun listDir(path: String): Array<String> {
        return buildList(path) { assetPath: String ->
            assetManager?.list(assetPath)?.size!! > 0
        }
    }

    //List all files in path, non-recursive
    fun listFiles(path: String): Array<String> {
        return buildList(path) { assetPath: String ->
            assetManager?.list(assetPath)?.size!! < 1
        }
    }

    //build list based on incoming boolean function
    private fun buildList(path: String, operation: (String) -> Boolean): Array<String> {
        val assetList = listAll(path)
        val dirs = emptyList<String>().toMutableList()

        if (assetList != null) {
            for (asset in assetList) {
                //build new path to file/directory
                val assetPath: String = if (path == "")
                    asset
                else
                    "$path/$asset"

                //check file/directory
                if (operation(assetPath))
                    dirs.add(asset)
            }
        }
        return dirs.toTypedArray()
    }

    fun openFd(fileName: String): AssetFileDescriptor? {
        return assetManager?.openFd(fileName)
    }
}