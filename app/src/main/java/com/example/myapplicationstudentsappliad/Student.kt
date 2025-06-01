package com.example.myapplicationstudentsappliad
data class Student(
    var name: String,
    var id: Int,
    var phone: Int,
    var address: String,
    var isChecked: Boolean = false,
    var avatarResId: Int
)