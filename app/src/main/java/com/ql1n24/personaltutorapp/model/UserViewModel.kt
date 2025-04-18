package com.ql1n24.personaltutorapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    // 当前登录用户数据
    var currentUser by mutableStateOf(
        User(
            id = "1",                     // 示例 ID，可按需设置
            role = "Tutor",              // 或 "Student"
            displayName = "默认昵称",
            bio = "这是一段简介",
            profileImageUrl = null
        )
    )
        private set

    // 更新用户信息
    fun updateUser(user: User) {
        currentUser = user
    }
}

