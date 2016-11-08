package ru.zurekat.taskslist.activities

 import android.content.Intent
 import android.database.Cursor
 import android.os.Bundle
import android.support.design.widget.FloatingActionButton
 import android.support.v4.app.LoaderManager
 import android.support.v4.content.CursorLoader
 import android.support.v4.content.Loader
 import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
 import android.widget.ArrayAdapter
 import android.widget.ListView
 import android.widget.SimpleCursorAdapter
 import com.activeandroid.content.ContentProvider
 import com.activeandroid.query.Select
 import ru.zurekat.taskslist.R
 import ru.zurekat.taskslist.adapters.TasksAdapter
 import ru.zurekat.taskslist.models.entities.Task
 import java.util.*

class TasksListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            val newTaskIntent = Intent(applicationContext, CreateTaskActivity::class.java)
            startActivity(newTaskIntent)
        }

        val listView = findViewById(R.id.tasks_list) as ListView
// Construct ArrayList for model type
//        val items = ArrayList<Task>()
// Construct adapter plugging in the array source
        val tasksAdapter = TasksAdapter(this)
// Query ActiveAndroid for list of data
        val queryResults = Select().from(Task::class.java)?.orderBy("title ASC")?.execute<Task>()
// Load the result into the adapter using `addAll`
        tasksAdapter.addAll(queryResults)
        listView.adapter = tasksAdapter

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
