package com.example.gamesensor

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity2 : AppCompatActivity(), SensorEventListener {
    var textView: TextView? = null
    var x = ArrayList<String>()
    var index: Int = 0
    var value: Int = 0

    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null
    var sensor2: Sensor? = null
    val TAG = "TYPE_PROXIMITY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // = intent.getStringArrayListExtra()
        x = intent.getStringArrayListExtra("AnswerArray") as ArrayList<String>


        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        /* if (sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
             sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
             print("$TAG is Found ..")

         } else {
             print("$TAG This Sensor is not found")
         }*/

        if (sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null && sensorManager!!.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
            ) != null
        ) {
            sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensor2 = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            print("$TAG is Found ..")
        } else {
            print("$TAG This Sensor is not found")
        }

        textView = findViewById(R.id.textView)
        /*x.add("1")
        x.add("2")
        x.add("3")
        x.add("4")
        x.add("5")
        x.add("6")*/

        //getItem()


    }

    /* fun getItem() {
         while (value>0) {
             index = Random.nextInt(x.size - 1)
             Log.e("hzm", index.toString())
             runOnUiThread { textView!!.setText("${x[index]}") }
             Thread.sleep(1000)
         }



     }*/

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type == Sensor.TYPE_PROXIMITY) {


            value = p0.values[0].toInt()

            runThread()


        }

        if (p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = p0.values[0]
            val y = p0.values[1]
            val z = p0.values[2]

            //textView1.setText("x : $x  \ny : $y  \nz : $z")
            if (z < 0) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager!!.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    private fun runThread() {
        object : Thread() {
            override fun run() {
                try {
                    while (value > 0) {
/*
                        for(i in 0..x.size-1){
                            index = i
                        }*/

                        index = Random.nextInt(x.size)
                        Log.e("hzm", index.toString())
                        runOnUiThread { textView!!.text = "${x[index]}" }
                        Thread.sleep(300)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

}
