package com.example.mvvmexample.data.models.viewModel


import kotlinx.coroutines.flow.*
import com.example.mvvmexample.data.models.Task
import kotlinx.coroutines.flow.StateFlow


class TaskListViewModel {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    init {
        // В реальном приложении тут можно загрузить данные из репозитория
        _tasks.value = listOf(
            Task(1, "Задача 1", "Описание задачи 1"),
            Task(2, "Задача 2", "Описание задачи 2"),
            Task(3, "Задача 3", "Описание задачи 3")
        )
    }

    fun addTask(task: Task) {
        _tasks.value += task
    }
    fun deleteTask(task: Task) {
        _tasks.value-=task
    }
}

