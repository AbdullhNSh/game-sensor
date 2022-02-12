package com.example.gamesensor

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {//, SensorEventListener {

    var AnswerArray = ArrayList<String>()

    /*   var index: Int = 0
       var value :Int = 0

       var sensorManager: SensorManager? = null
       var sensor: Sensor? = null
       val TAG = "TYPE_PROXIMITY"
   */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

         if (sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
             sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
             print("$TAG is Found ..")

         } else {
             print("$TAG This Sensor is not found")
         }*/

        btnAdd.setOnClickListener {
            if (txtAnswer.text.toString() == "") {
                Toast.makeText(this, "ادخل السؤال", Toast.LENGTH_SHORT).show()
            } else {
                AnswerArray.add(txtAnswer.text.toString())
                txtNum.text = "عدد الاسئلة :  ${(AnswerArray.size)}"
                txtAnswer.setText("")
            }


        }
        btnPlay.setOnClickListener {
            //    if ()
            if (AnswerArray.size < 2) {
                Toast.makeText(this, "ادخل سؤالين على الاقل", Toast.LENGTH_SHORT).show()

            } else {

                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("AnswerArray", AnswerArray)
                startActivity(intent)
                /*  Toast.makeText(this,"${AnswerArray.size}",Toast.LENGTH_SHORT).show()

                  Log.e("hzm","${AnswerArray.size}")
  /*for (x in 0..AnswerArray.size-1){
      Log.e("hzm",x.toString()  +  ": ${AnswerArray[x]}")
      txtArray.setText("${AnswerArray[x]}")
      Thread.sleep(1000)

  }*/

                  for (i in 0 until AnswerArray.size) {
                   //   txtArray.setText("" + AnswerArray.get(i).toString())

                   //   ( findViewById(AnswerArray[i])).setText( i + "" );
                     // Thread.sleep(4000)
                     // findViewById<TextView>(R.id.txtArray).setText("$i")
                      runOnUiThread { txtArray.setText("#${AnswerArray[i]}") }

                      //email.append(AnswerArray.get(i).toString())
                      Log.e("hzm",i.toString()  +  ": ${AnswerArray[i]}")
                      Thread.sleep(1000)
                    //  txtArray.append(AnswerArray.sorted().toString())
                     // txtArray.append("\n")
                  }

                /*  val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                      this,
                      android.R.layout.simple_list_item_1,
                      AnswerArray
                  )
                  txtArray.setAdapter(arrayAdapter)
  */
              }*/


            }

        }
    }
    /* override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

     }

     override fun onSensorChanged(p0: SensorEvent?) {
         if (p0?.sensor?.type == Sensor.TYPE_PROXIMITY) {



             value = p0.values[0].toInt()

             runThread()


         }
     }
     override fun onResume() {
         super.onResume()
         sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
     }

     override fun onPause() {
         super.onPause()
         sensorManager!!.unregisterListener(this)
     }

     private fun runThread() {
         object : Thread() {
             override fun run() {
                 try {
                     //   getItem()
                     while (value>0) {
                         index = Random.nextInt(x.size - 1)
                         Log.e("hzm", index.toString())
                         runOnUiThread { textView!!.setText("${x[index]}") }
                         Thread.sleep(1000)
                     }

                 } catch (e: InterruptedException) {
                     e.printStackTrace()
                 }
                 // }
             }
         }.start()
     }

 }*/
}