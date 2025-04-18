package com.ql1n24.personaltutorapp.model

data class Lesson(
    val id: Int,
    val title: String,
    var content: String = "",
    val imageUrl: String? = null, // 可选的图片地址
    var isCompleted: Boolean = false
)
