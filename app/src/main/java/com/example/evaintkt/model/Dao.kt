package com.example.evaintkt.model

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask (task: Entity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTask(ListTask: List<Entity>)

    @Update
    suspend fun updateTask(task: Entity)

    @Delete
    suspend fun deleteTask(task: Entity)

    @Query("DELETE FROM consume_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM consume_table ORDER BY id DESC")
    fun getAllTask() : LiveData<List<Entity>>


    @Query("SELECT * FROM consume_table WHERE item = :nombre LIMIT 1")
    fun getTaskByNombre(nombre: String) : LiveData<Entity>

    @Query("SELECT * FROM consume_table WHERE id = :id")
    fun getTaskById(id: Int) : LiveData<Entity>

}