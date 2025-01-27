package com.example.viewsandsensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewsandsensor.Views.Something
class MainActivity : AppCompatActivity(),SensorEventListener{
    protected lateinit var manager: SensorManager;
    protected lateinit var sensor: Sensor;
    protected lateinit var some:Something;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        some =  Something(this);
        setContentView(some);
        manager = getSystemService(SENSOR_SERVICE) as SensorManager;
        sensor = manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(p0: SensorEvent) {
        some.change = p0.values[0].toFloat();

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }
}