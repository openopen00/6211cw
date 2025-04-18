package com.ql1n24.personaltutorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ql1n24.personaltutorapp.model.CourseViewModel
import com.ql1n24.personaltutorapp.ui.theme.PersonalTutorApp6239Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ 创建共享的 ViewModel 实例
        val courseViewModel: CourseViewModel by viewModels()

        setContent {
            PersonalTutorApp6239Theme {
                AppNavigation(courseViewModel = courseViewModel)
            }
        }
    }
}
