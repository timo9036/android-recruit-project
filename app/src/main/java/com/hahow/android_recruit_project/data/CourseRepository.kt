package com.hahow.android_recruit_project.data

import com.hahow.android_recruit_project.model.Course

class CourseRepository (private val dataLoader: DataLoader) {

    suspend fun remoteDataSource(): List<Course> {
        return dataLoader.loadCourses()
    }
}