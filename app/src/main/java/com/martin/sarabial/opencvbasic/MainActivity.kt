package com.martin.sarabial.opencvbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import org.opencv.android.*
import org.opencv.core.Mat

class MainActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {
    val TAG = "Ejemplo OCV"

    lateinit var cameraView: CameraBridgeViewBase
    val loaderCallback: BaseLoaderCallback  = object :BaseLoaderCallback(baseContext){
        override fun onManagerConnected(status: Int) {
            when(status){
                LoaderCallbackInterface.SUCCESS-> {
                   Log.i(TAG, "OpenCV se cargo correctamente")
                }

                else -> {
                    super.onManagerConnected(status)
                }

            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_main)
        cameraView =  findViewById(R.id.vista_camara) as CameraBridgeViewBase
        cameraView.setCvCameraViewListener(this)
    }

    override fun onCameraViewStarted(width: Int, height: Int) {}

    override fun onCameraViewStopped() {}

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame?): Mat {
        return   inputFrame!!.rgba()
    }

    override fun onPause() {
        super.onPause()
        if(cameraView != null){
            cameraView.disableView()
        }
    }

    override fun onResume() {
        super.onResume()
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13,this, loaderCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(cameraView!= null){
            cameraView.disableView()
        }
    }


    fun onPackageInstall(operation:Int, callBack: InstallCallbackInterface ){}


}