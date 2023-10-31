package com.example.mvvmexample.ui.theme.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmexample.data.models.Task
import com.example.mvvmexample.data.models.viewModel.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListView(viewModel: TaskListViewModel) {
    var newTaskTitle by remember { mutableStateOf(TextFieldValue()) }
    val tasks by viewModel.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Список задач") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Список задач
        tasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = task.title)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { viewModel.deleteTask(task) }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Поле ввода новой задачи
        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            label = { Text("Название задачи") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка добавления новой задачи
        OutlinedButton(
            onClick = {
                viewModel.addTask(Task(tasks.size + 1, newTaskTitle.text, ""))
                newTaskTitle = TextFieldValue()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text("Добавить задачу")
        }
    }
}

@Preview
@Composable
fun PreviewTaskListView() {
    TaskListView(TaskListViewModel())
}
