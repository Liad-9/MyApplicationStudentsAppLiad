package com.example.myapplicationstudentsappliad

import com.example.myapplicationstudentsappliad.R

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    init {
        if (students.isEmpty()) {
            for (i in 0..3) {
                val student = Student(
                    name = "Name $i",
                    id = i,
                    phone = i,
                    address = "Address $i",
                    isChecked = false,
                    avatarResId = R.drawable.avatars_student
                )
                students.add(student)
            }
        }
    }

    companion object {
        val shared = Model()
    }
}