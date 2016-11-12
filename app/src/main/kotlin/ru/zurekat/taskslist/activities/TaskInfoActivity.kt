package ru.zurekat.taskslist.activities

import android.content.Intent


import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import ru.zurekat.taskslist.R
import ru.zurekat.taskslist.models.entities.Task

const val EXTRA_NAME_TASK_ID = "extra_task_id"

class TaskInfoActivity : AppCompatActivity() {

    var viewedTaskId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_info)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitle(R.string.activity_title_task_info)
        setSupportActionBar(toolbar)

        val extras = intent.extras
        if (extras == null) {
            finishActivity()
        }
        viewedTaskId = extras.getLong(EXTRA_NAME_TASK_ID)
        val task: Task = Select().from(Task::class.java).where("_ID = ?", viewedTaskId).executeSingle()

        val titleView = findViewById(R.id.task_info_title) as TextView
        titleView.text = task.title

        val textView = findViewById(R.id.task_info_description) as TextView
        textView.text = task.text
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task_info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            R.id.action_delete -> {
                AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_warning_black_24dp)
                        .setTitle(R.string.delete_task_confirmation_title)
                        .setMessage(R.string.delete_task_confirmation_message)
                        .setPositiveButton(R.string.yes, {
                            dialog, which -> finish()
                            Delete()
                                    .from(Task::class.java)
                                    .where("_ID = ?", viewedTaskId)
                                    .execute<Task>()
                            Toast.makeText(applicationContext, R.string.delete_task_success, Toast.LENGTH_SHORT).show()
                            finishActivity()
                        })
                        .setNegativeButton(R.string.no, null)
                        .show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun finishActivity() {
        val newTaskIntent = Intent(applicationContext, TasksListActivity::class.java)
        startActivity(newTaskIntent)
        finish()
    }
}

