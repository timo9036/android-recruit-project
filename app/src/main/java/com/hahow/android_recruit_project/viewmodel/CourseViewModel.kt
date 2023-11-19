package com.hahow.android_recruit_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hahow.android_recruit_project.data.CourseRepository
import com.hahow.android_recruit_project.model.Course
import kotlinx.coroutines.launch

class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    private var _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>>
        get() = _courses

    init {
        loadCourses()
    }

    private fun loadCourses() = viewModelScope.launch {
        val data = courseRepository.remoteDataSource()
        _courses.value = data
    }

}