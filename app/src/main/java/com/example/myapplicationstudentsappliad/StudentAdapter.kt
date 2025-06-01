package com.example.myapplicationstudentsappliad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private var students: List<Student>,
    private val onItemClick: (studentIndex: Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImage: ImageView = itemView.findViewById(R.id.student_avatar_image)
        val nameText: TextView = itemView.findViewById(R.id.student_name_text)
        val idText: TextView = itemView.findViewById(R.id.student_id_text)
        val checkBox: CheckBox = itemView.findViewById(R.id.student_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_student_recycler_view, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.nameText.text = student.name
        holder.idText.text = "ID: ${student.id}"
        holder.avatarImage.setImageResource(student.avatarResId)

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = student.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}