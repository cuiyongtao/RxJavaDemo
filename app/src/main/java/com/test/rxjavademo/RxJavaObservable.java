package com.test.rxjavademo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.test.rxjavademo.bean.RxJavaBean;
import com.test.rxjavademo.bean.Student;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.io.File;

/**
 * @Author： Victory
 * @Time： 2018/11/5
 * @QQ： 949021037
 * @Explain： com.test.androiddemo.activity.util
 */
public interface RxJavaObservable {
    /**
     * 字符串/整型/浮点/布尔类型类似
     */
    Observable<String> observableString = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            int i = 0;
            while (i < 10) {
                emitter.onNext("数据" + i);
                Thread.sleep(1000);
                i++;
            }
            emitter.onComplete();
        }
    });
//    }).subscribeOn(Schedulers.newThread())
//            //不管用了几个subscribeOn最终只第一次有效
//            //.subscribeOn(Schedulers.io());
//            //集成RxAndroid
//            .observeOn(AndroidSchedulers.mainThread());

    /**
     * 图片
     */
    Observable<Bitmap> observableBitmap = Observable.create(new ObservableOnSubscribe<Bitmap>() {
        @Override
        public void subscribe(ObservableEmitter<Bitmap> emitter) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tt.jpg");
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + "/tt.jpg");
                emitter.onNext(bitmap);
            }
            emitter.onComplete();
        }
    });

    /**
     * 泛型
     */

    Observable observableT = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter emitter) throws Exception {
            int i = 0;
            while (i < 3) {
                RxJavaBean rxJavaBean = new RxJavaBean(i, "name" + i, i + 20);
                emitter.onNext(rxJavaBean);
                i++;
            }
            emitter.onComplete();
        }
    });

    /**
     * 快捷传递序列/just 最多十个参数
     */
    Observable observableJust = Observable.just("sda", 1, "测试", 1, "fdfsa", "的说法都是佛法就仨房", 1, "dafdsf", 1, "sdfafds");

    /**
     * 跟Just类似，传入数组，但非限制数量
     */
    String[] stringArray = {"dddsass", "fsdfsf", "dsfaf"};
    Observable observableFrom = Observable.fromArray(stringArray);


    /**
     * map转换/仅限一对一转换
     */
    Observable<Bitmap> observableMap = Observable.just(Environment.getExternalStorageDirectory().getPath() + "/tt.jpg").map(new Function<String, Bitmap>() {
        @Override
        public Bitmap apply(String s) throws Exception {
            File file = new File(s);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(s);
                return bitmap;
            } else {
                return null;
            }
        }
    });

    /**
     * FlatMap转换
     */
    Student.Course course = new Student.Course("java");
    Student.Course course1 = new Student.Course("C#");
    Student.Course course2 = new Student.Course(".NET");
    Student.Course course3 = new Student.Course("Python");
    Student.Course course4 = new Student.Course("PHP");
    Student.Course[] courseList = {course, course1, course2, course3, course4};
    Student student = new Student("A", courseList);
    Observable<Student.Course> observableFlatMap = Observable.fromArray(student).flatMap(new Function<Student, ObservableSource<Student.Course>>() {
        @Override
        public ObservableSource<Student.Course> apply(Student student) throws Exception {
            return Observable.fromArray(student.getmCourseList());
        }
    });
}
