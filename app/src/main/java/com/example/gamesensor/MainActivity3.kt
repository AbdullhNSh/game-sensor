package com.example.gamesensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() , SensorEventListener {

    var sensorManager: SensorManager? = null
    var sensorMag: Sensor? = null
    var sensorAcc: Sensor? = null
    val TAG = "TYPE_PROXIMITY"

    val rotaionMitrix:ArrayList<Float>? =null
    //var arrayAcc: ArrayList<Float>? =
    var arrayAcc=ArrayList<Float>()
    val arrayMf :ArrayList<Float>? =null
    val values :ArrayList<Float>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)




        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager



        if (sensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null && sensorManager!!.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
            ) != null
        ) {
            sensorMag = sensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            sensorAcc = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            print("$TAG is Found ..")
        } else {
            print("$TAG This Sensor is not found")
        }


    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            arrayAcc!![0] = p0.values[0]
            arrayAcc!![1] = p0.values[1]
            arrayAcc!![2] = p0.values[2]
            // arrayAcc =  p0.values
            // arrayAcc[0]=x
            //arrayAcc=p0.values


        }else if(p0?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD){
            arrayMf!![0] = p0.values[0]

        }
      //  SensorManager.getRotationMatrix(rotaionMitrix!!.toFloatArray(),null,arrayAcc.toFloatArray(),arrayMf!!.toFloatArray())
      //  SensorManager.getOrientation(rotaionMitrix!!.toFloatArray(),values!!.toFloatArray())

        //Log.e("hzm",">>>>>>>>>>>>>>>>>"+values[0])

    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorMag, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensorAcc, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}