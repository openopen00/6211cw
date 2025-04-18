package com.ql1n24.personaltutorapp.model

data class User(
    var id: String = "",
    var role: String = "", // Tutor 或 Student
    var displayName: String = "",
    var bio: String = "",
    var profileImageUrl: String? = null // 存头像的 URL（可为空）
)
