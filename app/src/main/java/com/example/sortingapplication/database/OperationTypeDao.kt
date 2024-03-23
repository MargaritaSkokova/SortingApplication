package com.example.sortingapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface OperationTypeDao {
    @Insert
    fun insert(type: OperationType)

    @Insert
    fun insertAll(types: List<OperationType>)

    @Update
    fun update(type: OperationType)

    @Delete
    fun delete(type: OperationType)

    @Query("SELECT * FROM operation_type_table WHERE id = :id")
    fun getById(id: UUID) : OperationType

    @Query("SELECT id FROM operation_type_table WHERE operation_name = :name")
    fun getIdByName(name: String) : Int

    @Query("SELECT * FROM operation_type_table")
    fun getAll() : List<OperationType>
}