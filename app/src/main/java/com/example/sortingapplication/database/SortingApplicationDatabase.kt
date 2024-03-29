package com.example.sortingapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Operations::class, OperationType::class], version = 3)
abstract class SortingApplicationDatabase : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao
    abstract fun operationTypeDao(): OperationTypeDao
}