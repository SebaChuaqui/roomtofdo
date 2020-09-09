package com.crisspian.roomexampletodo_04_09

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.crisspian.roomexampletodo_04_09.database.Task
import com.crisspian.roomexampletodo_04_09.database.TaskDatabase
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TaskRepository
    var allTask: LiveData<List<Task>>

    init {
        var taskDao = TaskDatabase.getDatabase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }

    fun insertTask(task: Task) = viewModelScope.launch { repository.insertTask(task) }

    fun deleteAllTask() = viewModelScope.launch {
        repository.deleteAll()
    }
}

