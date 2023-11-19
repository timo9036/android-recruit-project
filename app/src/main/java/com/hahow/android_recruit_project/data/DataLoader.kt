package com.hahow.android_recruit_project.data

import com.hahow.android_recruit_project.model.Course

interface DataLoader {
    suspend fun loadCourses(): List<Course>
}