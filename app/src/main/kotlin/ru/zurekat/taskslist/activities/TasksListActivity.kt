package ru.zurekat.taskslist.activities

 import android.content.Intent
 import android.os.Bundle
 import android.support.design.widget.FloatingActionButton
 import android.support.v7.app.AppCompatActivity
 import android.support.v7.widget.Toolbar
 import android.view.Menu
 import android.view.MenuItem
 import android.widget.AdapterView
 import android.widget.ListView
 import com.activeandroid.query.Select
 import ru.zurekat.taskslist.R
 import ru.zurekat.taskslist.adapters.TasksAdapter
 import ru.zurekat.taskslist.models.entities.Task

class TasksListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_list)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitle(R.string.activity_title_tasks_list)
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            val newTaskIntent = Intent(applicationContext, CreateTaskActivity::class.java)
            startActivity(newTaskIntent)
        }

        val listView = findViewById(R.id.tasks_list) as ListView
        val tasksAdapter = TasksAdapter(this)
        val queryResults = Select().from(Task::class.java)?.orderBy("change_date DESC")?.execute<Task>()
        tasksAdapter.addAll(queryResults)
        listView.adapter = tasksAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { TasksAdapter, view, index, id ->
            val newTaskIntent = Intent(applicationContext, TaskInfoActivity::class.java)
            newTaskIntent.putExtra(EXTRA_NAME_TASK_ID, id)
            startActivity(newTaskIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tasks_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return super.onOptionsItemSelected(item)
    }
}
