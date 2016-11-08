package ru.zurekat.taskslist

import com.activeandroid.ActiveAndroid
import com.activeandroid.app.Application

/**
 * Created by BelyiZ
 * 07.11.2016
 */
class TasksListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ActiveAndroid.initialize(this);
    }
}