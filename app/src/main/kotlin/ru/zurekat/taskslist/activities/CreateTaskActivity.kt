package ru.zurekat.taskslist.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.zurekat.taskslist.R
import ru.zurekat.taskslist.models.entities.Task
import java.util.*

class CreateTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitle(R.string.activity_title_create_task)
        setSupportActionBar(toolbar)

        val saveBtn = findViewById(R.id.save_task_btn) as Button
        saveBtn.setOnClickListener({ view ->
            val taskTitleEditText = findViewById(R.id.task_title) as EditText
            val title = taskTitleEditText.text.toString()

            val taskDescriptionEditText = findViewById(R.id.task_description) as EditText
            val text: String = taskDescriptionEditText.text.toString()

            if (title.isBlank()) {
                val inputEmpty = getString(R.string.error_create_title_empty)

                Toast.makeText(applicationContext, inputEmpty, Toast.LENGTH_LONG).show()
            } else {
                val task = Task(title.trim(), text.trim(), Date())
                task.save()
                startActivity(Intent(this, TasksListActivity::class.java))
                finish()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_task_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
