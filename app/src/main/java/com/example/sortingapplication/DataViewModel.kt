package com.example.sortingapplication

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.sortingapplication.database.OperationType
import com.example.sortingapplication.database.Operations
import com.example.sortingapplication.database.SortingApplicationRepository
import java.util.UUID

class DataViewModel : ViewModel() {
    lateinit var repository: SortingApplicationRepository
    private var counter = 1
    fun formatTypeList(data: List<OperationType>): String {
        return data.fold("") { str, item ->
            str + '\n' + formatType(item)
        }
    }

    fun formatType(data: OperationType): String {
        return "${data.operationName} : id = ${data.id}"
    }

    fun formatOperationsList(data: List<Operations>): String {
        return data.fold("") { str, item ->
            str + '\n' + formatOperations(item)
        }
    }

    fun formatOperations(data: Operations): String {
        val operation = if (data.typeOperationId == repository.getTypeIdByName("Shift")) "Shift" else "Sort"
        return "${counter++}. $operation, ${data.arrayBefore} -> ${data.arrayAfter}"
    }

    fun outputDataTypes(view: TextView) {
        repository = SortingApplicationRepository.get()
        val tableOutput: List<Operations> = repository.getAllOperations()
        var stringText: String = formatOperationsList(tableOutput)
        view.text = stringText
    }
}