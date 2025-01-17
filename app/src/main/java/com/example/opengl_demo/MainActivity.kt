package com.example.opengl_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //private lateinit var myRender : MyRender
    //private lateinit var testCameraHelper : CameraHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("opencv:",stringFromJNI())
        record.setOnClickListener { m_GLSurfaceView.startRecord() }
        stop.setOnClickListener { m_GLSurfaceView.stopRecord() }
        bigeye.setOnCheckedChangeListener {
                buttonView, isChecked -> m_GLSurfaceView.enableBigEye(isChecked)
        }
    }

    fun testCamera() {
//            textureView.surfaceTextureListener = ( object : TextureView.SurfaceTextureListener {
//            override fun onSurfaceTextureAvailable(
//                surface: SurfaceTexture?,
//                width: Int,
//                height: Int
//            ) {
//                testCameraHelper = CameraHelper(baseContext)
//                testCameraHelper.setSurface(Surface(textureView.surfaceTexture))
//                testCameraHelper.openCamera()
//            }
//
//            override fun onSurfaceTextureSizeChanged(
//                surface: SurfaceTexture?,
//                width: Int,
//                height: Int
//            ) {
//
//            }
//
//            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
//                return true
//            }
//
//            override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
//            }
//        })
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
