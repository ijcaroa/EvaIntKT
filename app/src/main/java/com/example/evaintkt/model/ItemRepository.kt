package com.example.evaintkt.model

import androidx.lifecycle.LiveData

class ItemRepository (private val Dao: Dao){

    val listAlltask : LiveData<List<Entity>> = Dao.getAllTask()

    suspend fun insertTask(task: Entity){
        Dao.insertTask(task)
    }
    suspend fun updateTask(task: Entity){
        Dao.updateTask(task)
    }
    suspend fun deleteAll(){
        Dao.deleteAll()
    }
    fun getTaskById (id: Int): LiveData<Entity>{
        return Dao.getTaskById(id)
    }
}