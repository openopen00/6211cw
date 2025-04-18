package com.ql1n24.personaltutorapp.model


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CourseViewModel : ViewModel() {
    // 课程列表
    val courses = mutableStateListOf<Course>()

    // 添加新课程
    fun addCourse(course: Course) {
        courses.add(course)
    }

    //删除课程
    fun removeCourse(course: Course) {
        courses.remove(course)
    }

}
