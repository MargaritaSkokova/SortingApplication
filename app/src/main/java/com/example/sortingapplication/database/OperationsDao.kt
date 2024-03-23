package com.example.sortingapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface OperationsDao {
    @Insert
    fun insert(operations: Operations)

    @Insert
    fun insertAll(operations: List<Operations>)

    @Update
    fun update(operations: Operations)

    @Delete
    fun delete(operations: Operations)

    @Query("SELECT * FROM OPERATIONS_TABLE WHERE operation_type_id = :id")
    fun getByType(id: Int) : List<Operations>

    @Query("SELECT * FROM OPERATIONS_TABLE WHERE id = :id")
    fun getById(id: UUID) : Operations

    @Query("SELECT * FROM OPERATIONS_TABLE")
    fun getAll() : List<Operations>
}