package com.example.myapplicationstudentsappliad

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class EditSFragment : Fragment() {

    private var listener: FragmentListener? = null
    private var studentIndex: Int = -1

    companion object {
        private const val ARG_STUDENT_INDEX = "student_index"

        fun newInstance(index: Int): EditSFragment {
            val fragment = EditSFragment()
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (studentIndex !in Model.shared.students.indices) {
            Toast.makeText(context, "Student not found.", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
            return
        }

        val student = Model.shared.students[studentIndex]

        val nameEdit = view.findViewById<EditText>(R.id.student_name_edit)
        val idEdit = view.findViewById<EditText>(R.id.student_id_edit)
        val phoneEdit = view.findViewById<EditText>(R.id.student_phone_edit)
        val addressEdit = view.findViewById<EditText>(R.id.address_student_edit)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox_edit)
        val saveButton = view.findViewById<Button>(R.id.save_e_button)
        val deleteButton = view.findViewById<Button>(R.id.delete_e_button)
        val cancelButton = view.findViewById<Button>(R.id.cancel_e_button)

        nameEdit.setText(student.name)
        idEdit.setText(student.id.toString())
        phoneEdit.setText(student.phone.toString())
        addressEdit.setText(student.address)
        checkBox.isChecked = student.isChecked

        saveButton.setOnClickListener {
            val updatedName = nameEdit.text.toString()
            val updatedId = idEdit.text.toString().toIntOrNull()
            val updatedPhone = phoneEdit.text.toString().toIntOrNull()
            val updatedAddress = addressEdit.text.toString()
            val isChecked = checkBox.isChecked

            if (updatedId == null || updatedPhone == null) {
                Toast.makeText(
                    context,
                    "Please enter valid ID and Phone numbers.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            student.name = updatedName
            student.id = updatedId
            student.phone = updatedPhone
            student.address = updatedAddress
            student.isChecked = isChecked

            listener?.refreshStudentList()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentListFragment())
                .commit()
        }

        deleteButton.setOnClickListener {
            Model.shared.students.removeAt(studentIndex)
            listener?.refreshStudentList()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentListFragment())
                .commit()
        }

        cancelButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StudentListFragment())
                .commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}