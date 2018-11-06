package com.test.rxjavademo.bean;

/**
 * @Author： Victory
 * @Time： 2018/11/6
 * @QQ： 949021037
 * @Explain： com.test.androiddemo.activity.bean
 */
public class Student {
    private String name;
    private Course[] mCourseList;

    public Student(String name, Course[] courseList) {
        this.name = name;
        this.mCourseList = courseList;
    }

    public Course[] getmCourseList() {
        return mCourseList;
    }

    public void setmCourseList(Course[] mCourseList) {
        this.mCourseList = mCourseList;
    }

    public static class Course {
        private String CourseName;

        public Course(String courseName) {
            this.CourseName = courseName;
        }

        public String getCourseName() {
            return CourseName;
        }
    }
}
