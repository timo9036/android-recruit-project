package com.hahow.android_recruit_project.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hahow.android_recruit_project.data.DataLoader
import com.hahow.android_recruit_project.viewmodel.CourseViewModel

class ViewModelFactory(private val dataLoader: DataLoader) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(dataLoader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}