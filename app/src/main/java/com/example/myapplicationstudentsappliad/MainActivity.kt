package com.example.myapplicationstudentsappliad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), FragmentListener {

    private var studentListFragment: StudentListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            studentListFragment = StudentListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, studentListFragment!!)
                .commit()
        } else {
            studentListFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? StudentListFragment
        }
    }

    override fun refreshStudentList() {
        studentListFragment?.refreshStudentList()
    }
}