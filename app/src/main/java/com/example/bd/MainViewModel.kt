package com.example.bd

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bd.data.AppDB
import com.example.bd.data.NameEntity
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainViewModel(val dataBase: AppDB) : ViewModel() {
    val itemsList = dataBase.dao.getAllItems()
    val newText = mutableStateOf("")
    var nameEntity: NameEntity? = null

    fun insertItem() = viewModelScope.launch {
        val nameItem = nameEntity?.copy(name = newText.value) ?: NameEntity(name = newText.value)
        dataBase.dao.insertItem(nameItem)
        nameEntity = null
        newText.value = ""
    }


    fun deleteItem(item: NameEntity) = viewModelScope.launch {
        dataBase.dao.deleteItem(item)
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                val dataBase = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MainViewModel(dataBase) as T
            }

        }

    }
}