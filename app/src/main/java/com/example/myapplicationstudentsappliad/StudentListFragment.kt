package com.example.myapplicationstudentsappliad

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListFragment : Fragment() {

    private var listener: FragmentListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.student_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = StudentAdapter(Model.shared.students) { studentIndex ->
            val fragment = DetailsFragment.newInstance(studentIndex)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter

        view.findViewById<View>(R.id.add_student_button).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddSFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(Model.shared.students)
    }

    fun refreshStudentList() {
        adapter.updateData(Model.shared.students)
    }
}
