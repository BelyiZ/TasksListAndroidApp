package ru.zurekat.taskslist.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.zurekat.taskslist.R
import ru.zurekat.taskslist.models.entities.Task

/**
 * Created by BelyiZ
 * 08.11.2016
 */
class TasksAdapter(context: Context) : ArrayAdapter<Task>(context, -1) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = convertView
        if (view == null) {
            view = createView()
        }
        val task = getItem(position)
        (view?.findViewById(R.id.task_title) as TextView).setText(task.title)
        (view?.findViewById(R.id.task_text) as TextView).setText(task.text)
        return view
    }

    fun createView() = View.inflate(getContext()!!, R.layout.task_list_item, null)

}