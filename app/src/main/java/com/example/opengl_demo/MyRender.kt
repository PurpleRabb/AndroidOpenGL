package com.example.opengl_demo

import android.graphics.SurfaceTexture
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import com.example.opengl_demo.filter.CameraFilter
import com.example.opengl_demo.util.CameraHelper
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyRender(myGLSufaceView: MyGLSurfaceView) : GLSurfaceView.Renderer {
    private lateinit var cameraHelper: CameraHelper
    private var mGLSurfaceView: MyGLSurfaceView = myGLSufaceView
    private lateinit var mTextureId: IntArray
    private lateinit var mSurfaceTexture: SurfaceTexture
    private lateinit var cameraFilter: CameraFilter

    private var tMatrix = FloatArray(16)

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        cameraHelper = CameraHelper(mGLSurfaceView.context)
        mTextureId = IntArray(1)
        glGenTextures(mTextureId.size, mTextureId, 0)
        mSurfaceTexture = SurfaceTexture(mTextureId[0])
        cameraFilter = CameraFilter(mGLSurfaceView.context, R.raw.camera_vertex, R.raw.camera_fragment)
        mSurfaceTexture.setOnFrameAvailableListener {
            mGLSurfaceView.requestRender() //这里发起渲染请求
        }
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        cameraHelper.openCamera(width, height, mSurfaceTexture)
        cameraFilter.setSize(width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        glClearColor(50f, 50f, 100f, 0f)
        glClear(GL_COLOR_BUFFER_BIT)

        mSurfaceTexture.updateTexImage()
        mSurfaceTexture.getTransformMatrix(tMatrix)
        cameraFilter.setMatrix(tMatrix)
        cameraFilter.draw(mTextureId)
    }
}