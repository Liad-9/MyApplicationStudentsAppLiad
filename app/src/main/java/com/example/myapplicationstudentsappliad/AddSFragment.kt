package com.example.myapplicationstudentsappliad

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplicationstudentsappliad.R


class AddSFragment : Fragment() {

    private var listener: FragmentListener? = null

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
        return inflater.inflate(R.layout.fragment_add_s, container, false)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val nameEdit = view.findViewById<EditText>(R.id.student_name)
            val idEdit = view.findViewById<EditText>(R.id.student_id)
            val phoneEdit = view.findViewById<EditText>(R.id.student_phone)
            val addressEdit = view.findViewById<EditText>(R.id.address_student)
            val checkBox = view.findViewById<CheckBox>(R.id.checkBox_add)
            val saveButton = view.findViewById<Button>(R.id.save_button)
            val cancelButton = view.findViewById<Button>(R.id.cancel_button)

            saveButton.setOnClickListener {
                val student = Student(
                    name = nameEdit.text.toString(),
                    id = idEdit.text.toString().toIntOrNull() ?: 0,
                    phone = phoneEdit.text.toString().toIntOrNull() ?: 0,
                    address = addressEdit.text.toString(),
                    isChecked = checkBox.isChecked,
                    avatarResId = R.drawable.avatars_student
                )
                Model.shared.students.add(student)
                listener?.refreshStudentList()
                parentFragmentManager.popBackStack()
            }

            cancelButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

}
