package ru.zurekat.taskslist.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.zurekat.taskslist.R
import ru.zurekat.taskslist.models.entities.Task

/**
 * Adapter for tasks list to show them in ListView
 *
 * Created by BelyiZ
 * 08.11.2016
 */
class TasksAdapter(context: Context) : ArrayAdapter<Task>(context, -1) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = createView()
        }
        val task = getItem(position)
        (view.findViewById(R.id.task_title) as TextView).text = task.title
        (view.findViewById(R.id.task_text) as TextView).text = task.text
        return view
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    /**
     * Create new view for task item
     */
    fun createView(): View = View.inflate(context!!, R.layout.task_list_item, null)
}