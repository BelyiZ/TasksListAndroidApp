package ru.zurekat.taskslist.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        val saveBtn = findViewById(R.id.save_task_btn) as Button

        saveBtn.setOnClickListener({ view ->
            val taskTitleEditText = findViewById(R.id.task_title) as EditText
            val title = taskTitleEditText.text.toString()

            val taskDescriptionEditText = findViewById(R.id.task_description) as EditText
            val text: String = taskDescriptionEditText.text.toString()

            if (title.isEmpty() or text.isEmpty()) {
                val inputEmpty = getString(R.string.error_input_empty)

                Toast.makeText(applicationContext, inputEmpty, Toast.LENGTH_LONG).show()
            } else {
                val task = Task(title, text, Date())
                val id = task.save()

                startActivity(Intent(this, TasksListActivity::class.java))

                Log.d("New Task", "ID: $id")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_task_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            R.id.action_delete -> {
                //delete task
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
