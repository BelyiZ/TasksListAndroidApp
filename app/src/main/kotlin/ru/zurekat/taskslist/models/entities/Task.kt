package ru.zurekat.taskslist.models.entities

import android.provider.BaseColumns
import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.util.*

/**
 * Created by BelyiZ
 * 07.11.2016
 */
@Table(name = "Tasks", id = BaseColumns._ID)
class Task : Model {

    @Column(name = "title")
    var title: String? = null

    @Column(name = "text")
    var text: String? = null

    @Column(name = "create_date")
    var createDate: Date? = null

    @Column(name = "change_date")
    var changeDate: Date? = null

    constructor(title: String, text: String, createDate: Date) {
        this.title = title
        this.text = text
        this.createDate = createDate
    }

    constructor()

    fun getInfo(): String = "Название:\n$title\n" +
            "Время создания:\n${createDate}\n" +
            "Время изменения:\n${changeDate}"
}