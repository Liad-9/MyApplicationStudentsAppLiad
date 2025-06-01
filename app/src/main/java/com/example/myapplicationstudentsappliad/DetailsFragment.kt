package com.example.myapplicationstudentsappliad

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment() {

    private var studentIndex: Int = -1
    private var listener: FragmentListener? = null

    companion object {
        private const val ARG_STUDENT_INDEX = "student_index"

        fun newInstance(index: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putInt(ARG_STUDENT_INDEX, index)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentIndex = arguments?.getInt(ARG_STUDENT_INDEX) ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (studentIndex !in Model.shared.students.indices) {
            Toast.makeText(context, "Invalid student index", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
            return
        }

        val student = Model.shared.students[studentIndex]

        val nameEdit = view.findViewById<EditText>(R.id.student_name_d)
        val idEdit = view.findViewById<EditText>(R.id.student_id_d)
        val phoneEdit = view.findViewById<EditText>(R.id.student_phone_d)
        val addressEdit = view.findViewById<EditText>(R.id.address_student_d)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox_details)
        val editButton = view.findViewById<Button>(R.id.edit_d_button)


        nameEdit.setText(student.name)
        idEdit.setText(student.id.toString())
        phoneEdit.setText(student.phone.toString())
        addressEdit.setText(student.address)
        checkBox.isChecked = student.isChecked


        listOf(nameEdit, idEdit, phoneEdit, addressEdit).forEach { it.isEnabled = false }
        checkBox.isEnabled = false

        editButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EditSFragment.newInstance(studentIndex))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
