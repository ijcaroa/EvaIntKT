package com.example.evaintkt.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.evaintkt.model.Dao
import com.example.evaintkt.model.Entity
import com.example.evaintkt.model.ItemDatabase
import com.example.evaintkt.model.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: ItemRepository
    val allTask : LiveData<List<Entity>>

    init {
        val dao = ItemDatabase.getDataBase(application).getDao()
        repository = ItemRepository(dao)
        allTask = repository.listAlltask
    }

    fun insertTask(task: Entity) = viewModelScope.launch {
        repository.insertTask(task)
    }
    fun updateTask(task: Entity) = viewModelScope.launch {
        repository.updateTask(task)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun getTaskById (id: Int): LiveData<Entity>{
        return repository.getTaskById(id)
    }



}