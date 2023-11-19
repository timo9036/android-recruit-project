package com.hahow.android_recruit_project.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hahow.android_recruit_project.model.Course
import com.hahow.android_recruit_project.model.CourseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonFileDataLoader (private val context: Context, private val fileName: String) : DataLoader {

    private val gson = Gson()

    override suspend fun loadCourses(): List<Course> = withContext(Dispatchers.IO) {
        try {
            val json = loadJsonFromFile()
            val courseType = object : TypeToken<CourseData>() {}.type
            gson.fromJson<CourseData>(json, courseType).data
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun loadJsonFromFile(): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}