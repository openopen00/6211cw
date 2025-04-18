package com.ql1n24.personaltutorapp.model
import java.util.UUID


data class Course(
    val id: String = UUID.randomUUID().toString(), // 👈 新增唯一 ID
    val title: String,
    val description: String,
    val subject: String,
    val lessons: MutableList<Lesson> = mutableListOf()
)
