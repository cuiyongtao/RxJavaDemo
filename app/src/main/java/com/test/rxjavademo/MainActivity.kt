package com.test.rxjavademo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import java.util.*

class MainActivity : AppCompatActivity(), RxJavaObservable {

    lateinit var btnRxJava: Button
    lateinit var btnRxJavaTest: Button
    lateinit var btnRxJavaString: Button
    lateinit var btnRxJavaT: Button
    lateinit var btnRxJavaBitmip: Button
    lateinit var btnRxJavaJust: Button
    lateinit var btnRxJavaFrom: Button
    lateinit var btnRxJavaMap: Button
    lateinit var btnRxJavaFlatMap: Button
    lateinit var rxImage: ImageView

    private val TAG = "RxJavaDemoActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        onClick()
    }

    private fun init() {
        btnRxJava = findViewById(R.id.btnRxJava)
        btnRxJavaTest = findViewById(R.id.btnRxJavaTest)
        btnRxJavaString = findViewById(R.id.btnRxJavaString)
        btnRxJavaT = findViewById(R.id.btnRxJavaT)
        btnRxJavaBitmip = findViewById(R.id.btnRxJavaBitmip)
        btnRxJavaJust = findViewById(R.id.btnRxJavaJust)
        btnRxJavaFrom = findViewById(R.id.btnRxJavaFrom)
        btnRxJavaMap = findViewById(R.id.btnRxJavaMap)
        btnRxJavaFlatMap = findViewById(R.id.btnRxJavaFlatMap)
        rxImage = findViewById(R.id.rxImage)
    }

    private fun onClick() {
        btnRxJava.setOnClickListener {
           Flowable.just("1","2","3","4").subscribe(Consumer {
               Log.d(TAG,"onNext:"+it);
           });
        }
        btnRxJavaTest.setOnClickListener {
            testRxJava()
        }
        btnRxJavaString.setOnClickListener {

        }
        btnRxJavaT.setOnClickListener {

        }
        btnRxJavaBitmip.setOnClickListener {

        }
        btnRxJavaJust.setOnClickListener {

        }
        btnRxJavaFrom.setOnClickListener {

        }
        btnRxJavaMap.setOnClickListener {

        }
        btnRxJavaFlatMap.setOnClickListener {

        }

    }

    private fun testRxJava() {

    }

}
