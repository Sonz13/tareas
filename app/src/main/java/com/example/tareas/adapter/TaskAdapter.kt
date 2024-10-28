package com.tuapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tareas.R
import com.tuapp.model.Task

class TaskAdapter(
    private val tasks: List<Task>,
    private val onTaskCompleted: (Task) -> Unit,
    private val onTaskDeleted: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTaskTitle: TextView = itemView.findViewById(R.id.textViewTaskTitle)
        val checkBoxCompleted: CheckBox = itemView.findViewById(R.id.checkBoxCompleted)
        val imageViewDelete: ImageView = itemView.findViewById(R.id.imageViewDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.textViewTaskTitle.text = task.title
        holder.checkBoxCompleted.isChecked = task.completed

        holder.checkBoxCompleted.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) onTaskCompleted(task)
        }

        holder.imageViewDelete.setOnClickListener {
            onTaskDeleted(task)
        }
    }

    override fun getItemCount(): Int = tasks.size
}
