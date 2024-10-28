package com.tuapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tareas.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tuapp.adapter.TaskAdapter
import com.tuapp.fragment.AddTaskFragment
import com.tuapp.model.TaskManager

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        taskAdapter = TaskAdapter(TaskManager.getTasks(),
            onTaskCompleted = { task ->
                TaskManager.completeTask(task)
                updateUI()
            },
            onTaskDeleted = { task ->
                TaskManager.deleteTask(task)
                updateUI()
            }
        )

        recyclerViewTasks.adapter = taskAdapter

        findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener {
            openAddTaskFragment()
        }
    }

    fun updateUI() {
        taskAdapter.notifyDataSetChanged()
    }

    private fun openAddTaskFragment() {
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.show(supportFragmentManager, "AddTaskFragment")
    }
}
