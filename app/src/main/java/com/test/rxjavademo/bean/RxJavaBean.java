package com.test.rxjavademo.bean;

/**
 * @Author： Victory
 * @Time： 2018/11/5
 * @QQ： 949021037
 * @Explain： com.test.androiddemo.activity.bean
 */
public class RxJavaBean {

    private int id;
    private String name;
    private int age;

    public RxJavaBean(int id, String name, int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }
}
