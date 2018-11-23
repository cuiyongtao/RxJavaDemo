package com.test.rxjavademo

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.test.rxjavademo.RxJavaObservable.*
import com.test.rxjavademo.bean.RxJavaBean
import com.test.rxjavademo.bean.Student
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

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
            Flowable.just("1", "2", "3", "4").subscribe(Consumer {
                Log.d(TAG, "onNext:$it");
            });
        }
        btnRxJavaTest.setOnClickListener {
            testRxJava()
        }
        btnRxJavaString.setOnClickListener {
            //简写（Kotlin特有，Java没发现可简写）
//            observableString.subscribe {
//                Log.d(TAG, "onNext:$it");
//            }

            observableString.subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    Log.d(TAG, "onNext:$t");
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }
            })
        }
        btnRxJavaT.setOnClickListener {
            observableT.subscribe(object : Observer<Any> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Any) {
                    var rejavaBean = t as RxJavaBean
                    Log.d(TAG, "onNext:${rejavaBean.name}${rejavaBean.age}");
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }

            })
        }
        btnRxJavaBitmip.setOnClickListener {
            observableBitmap.subscribe(object : Observer<Bitmap> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Bitmap) {
                    rxImage.setImageBitmap(t)
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }
            })
        }
        btnRxJavaJust.setOnClickListener {
            observableJust.subscribe(object : Observer<Any> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Any) {
                    Log.d(TAG, "onNext:${t.toString()}");
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }
            })
        }
        btnRxJavaFrom.setOnClickListener {
            observableFrom.subscribe(object : Observer<Any> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Any) {
                    Log.d(TAG, "onNext:${t.toString()}");
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }
            })
        }
        btnRxJavaMap.setOnClickListener {
            observableMap.subscribe(object : Observer<Any> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Any) {
                    var bitmap=t as Bitmap
                    rxImage.setImageBitmap(bitmap)
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }
            })
        }
        btnRxJavaFlatMap.setOnClickListener {
            observableFlatMap.subscribe(object :Observer<Student.Course>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Student.Course) {
                    Log.d(TAG, "onNext:${t.courseName}");
                }

                override fun onComplete() {
                    Log.d(TAG, "onNext:Complete");
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext:${e.toString()}");
                }
            })
        }

    }

    private fun testRxJava() {
        //简写
//        Observable.create(ObservableOnSubscribe<String> {
//            var i = 0;
//            while (i < 10) {
//                it.onNext("数据$i")
//                Thread.sleep(1000)
//                i++
//            }
//            it.onComplete()
//        }).subscribe {
//            Log.d(TAG, "onNext:$it")
//        }

        Observable.create(ObservableOnSubscribe<String> { emitter ->
            try {
                var i = 0
                while (i < 10) {
                    emitter.onNext("数据$i")
                    Thread.sleep(1000)
                    i++
                }
                emitter.onComplete()
            } catch (e: InterruptedException) {
                Log.e(TAG, "onNext:${e.toString()} ")
            }
        }).subscribeOn(Schedulers.newThread()).
            observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<String> {
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext:$t")
            }

            override fun onSubscribe(d: Disposable) {

            }
        })

    }

}
