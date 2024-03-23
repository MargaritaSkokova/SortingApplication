package com.example.sortingapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "operations_table",
//    foreignKeys = [
//        ForeignKey(
//            entity = OperationType::class,
//            parentColumns = ["id"],
//            childColumns = ["operation_type_id"]
//        )
//    ]
)
data class Operations(
    @PrimaryKey
    var id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "operation_type_id")
    var typeOperationId: Int = 0,

    @ColumnInfo(name = "array_before")
    var arrayBefore: String = "",

    @ColumnInfo(name = "array_after")
    var arrayAfter: String = ""
)
