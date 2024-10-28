package com.tuapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tuapp.MainActivity
import com.tuapp.model.TaskManager
import com.example.tareas.R

class AddTaskFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        val buttonAddTask = view.findViewById<Button>(R.id.buttonAddTask)
        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)

        buttonAddTask.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                TaskManager.addTask(title, description)
                (activity as MainActivity).updateUI()
                dismiss()
            } else {
                Toast.makeText(context, "Please enter both title and description", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
