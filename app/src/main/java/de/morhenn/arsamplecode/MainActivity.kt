package de.morhenn.arsamplecode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.sceneview.ar.ARCore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARCore.registerForCameraPermissionResult(this)

    }
}