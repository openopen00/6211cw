package com.ql1n24.personaltutorapp.ui.screens

import androidx.lifecycle.viewmodel.compose.viewModel
import com.ql1n24.personaltutorapp.model.CourseViewModel


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ql1n24.personaltutorapp.Screen

import com.ql1n24.personaltutorapp.model.Course

import android.util.Log

import com.ql1n24.personaltutorapp.model.Lesson





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCourseScreen(navController: NavController,
                       courseViewModel: CourseViewModel) {
    val context = LocalContext.current

    var courseTitle by remember { mutableStateOf("") }
    var courseDescription by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }




    Scaffold(
        topBar = {
            TopAppBar(title = { Text("创建新课程") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = courseTitle,
                onValueChange = { courseTitle = it },
                label = { Text("课程名称") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = courseDescription,
                onValueChange = { courseDescription = it },
                label = { Text("课程简介") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("学科分类") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (courseTitle.isBlank() || subject.isBlank()) {
                        Toast.makeText(context, "课程名称和分类不能为空", Toast.LENGTH_SHORT).show()
                    } else {
                        val newCourse = Course(
                            title = courseTitle,
                            description = courseDescription,
                            subject = subject,
                            lessons = mutableListOf(
                                Lesson(
                                    1,
                                    "简介与目标",
                                    "这节介绍课程结构",
                                    imageUrl = "https://picsum.photos/300"
                                ),
                                Lesson(
                                    2,
                                    "基础概念",
                                    "讲解基本知识",
                                    imageUrl = "https://picsum.photos/301"
                                )
                            )
                        )


                        courseViewModel.addCourse(newCourse)
                        //后添加
                        println("添加成功后的课程列表: ${courseViewModel.courses}")


                        // 👇 添加这一行
                        //println("当前课程列表：${courseViewModel.courses}")
                        Log.d("课程调试", "课程数量：${courseViewModel.courses.size}")
                        courseViewModel.courses.forEach {
                            Log.d("课程调试", "标题=${it.title}, 简介=${it.description}, 分类=${it.subject}")
                        }




                        Toast.makeText(context, "课程已创建", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("创建课程")
            }

        }
    }






}


