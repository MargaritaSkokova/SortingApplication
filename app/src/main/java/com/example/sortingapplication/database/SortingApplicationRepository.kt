package com.example.sortingapplication.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.UUID
import java.util.concurrent.Executors

class SortingApplicationRepository private constructor(context: Context) {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("INSERT INTO operation_type_table VALUES (null, 'Sort') ")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("DROP TABLE operations_table")
            db.execSQL("CREATE TABLE 'operations_table' ('id' BLOB NOT NULL, 'operation_type_id' INTEGER NOT NULL, 'array_before' TEXT NOT NULL, 'array_after' TEXT NOT NULL, PRIMARY KEY ('id'))")
        }
    }

    private val database: SortingApplicationDatabase = Room.databaseBuilder(
        context.applicationContext,
        SortingApplicationDatabase::class.java,
        "db"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL("INSERT INTO operation_type_table VALUES (null, 'Sort') ")
            db.execSQL("INSERT INTO operation_type_table VALUES (null, 'Shift') ")
        }
    }).allowMainThreadQueries().addMigrations(MIGRATION_1_2).addMigrations(MIGRATION_2_3).build()

    var executor = Executors.newSingleThreadExecutor()
    val operationsDao: OperationsDao = database.operationsDao()
    val typesDao: OperationTypeDao = database.operationTypeDao()


    companion object {
        private var instance: SortingApplicationRepository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = SortingApplicationRepository(context)
            }
        }

        fun get(): SortingApplicationRepository {
            return instance ?: throw IllegalArgumentException("huy")
        }
    }

    fun getAllTypes(): List<OperationType> {
        return typesDao.getAll();
    }

    fun getTypeIdByName(name: String): Int {
        return typesDao.getIdByName(name)
    }

    fun getAllOperations(): List<Operations> {
        return operationsDao.getAll();
    }

    fun insertType(type: OperationType) {
        executor.execute {
            typesDao.insert(type)
        }
    }

    fun getTypeById(id: UUID): OperationType {
        return typesDao.getById(id)
    }

    fun getOperationById(id: UUID): Operations {
        return operationsDao.getById(id)
    }

    fun insertOperation(operationId: Int, arrayBefore: String, arrayAfter: String) {
        operationsDao.insert(
            Operations(
                typeOperationId = operationId,
                arrayBefore = arrayBefore,
                arrayAfter = arrayAfter
            )
        )
    }
}