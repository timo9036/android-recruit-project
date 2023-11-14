package com.hahow.android_recruit_project

data class CourseData(
    val data: List<Course>
)

data class Course(
    val successCriteria: SuccessCriteria,
    val numSoldTickets: Int,
    val status: String,
    val proposalDueTime: String?,
    val coverImageUrl: String,
    val title: String
)

data class SuccessCriteria(
    val numSoldTickets: Int
)
