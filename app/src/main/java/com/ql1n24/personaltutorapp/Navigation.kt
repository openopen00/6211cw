package com.ql1n24.personaltutorapp

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column    // ✅ 显式导入 Column
import androidx.compose.material3.Text           // ✅ 显式导入 Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.*
import com.ql1n24.personaltutorapp.ui.screens.*
import com.ql1n24.personaltutorapp.model.CourseViewModel


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.lifecycle.viewmodel.compose.viewModel
import com.ql1n24.personaltutorapp.model.UserViewModel





// ✅ 页面定义
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object RoleSelection : Screen("role_selection")
    object TutorHome : Screen("tutor_home")
    object StudentHome : Screen("student_home")
    object CreateCourse : Screen("create_course")

    // ✅ 使用课程 ID（字符串）
    object CourseDetail : Screen("course_detail/{courseId}") {
        fun routeWithId(id: String) = "course_detail/$id"
    }
    // ✅ 添加这个
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    courseViewModel: CourseViewModel
) {
    val userViewModel: UserViewModel = viewModel() // ✅ 添加这一行

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.SignUp.route) { SignUpScreen(navController) }
        composable(Screen.RoleSelection.route) { RoleSelectionScreen(navController) }
        composable(Screen.TutorHome.route) { TutorHomeScreen(navController, courseViewModel, userViewModel) }// ✅ 传入 userViewModel }
        composable(Screen.StudentHome.route) { StudentHomeScreen(navController) }
        composable(Screen.CreateCourse.route) { CreateCourseScreen(navController, courseViewModel) }

        // ✅ 课程详情页（通过 ID 查找）
        composable(
            route = Screen.CourseDetail.route,
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            val course = courseViewModel.courses.find { it.id == courseId }

            if (course != null) {
                CourseDetailScreen(course = course, navController = navController)
            } else {
                Scaffold(
                    content = { padding ->
                        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                            Text("⚠ 课程未找到，可能已被删除", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                )

            }
        }

        composable(Screen.Profile.route) {
            val userViewModel: UserViewModel = viewModel()
            UserProfileScreen(
                user = userViewModel.currentUser,
                onSave = { updatedUser -> userViewModel.updateUser(updatedUser) }
            )
        }

    }
}
