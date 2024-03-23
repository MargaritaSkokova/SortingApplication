package com.example.sortingapplication

import androidx.lifecycle.ViewModel
import com.example.sortingapplication.database.OperationTypeDao
import com.example.sortingapplication.database.OperationsDao
import com.example.sortingapplication.database.SortingApplicationRepository

class ShiftResultViewModel(): ViewModel() {

    fun stringToArray(numbers: String): List<String> {
        var arrayNumbers = numbers.split("\\s+".toRegex())
        if (arrayNumbers[arrayNumbers.size - 1] == "" || arrayNumbers[arrayNumbers.size - 1] == " ") {
            arrayNumbers = arrayNumbers.subList(0, arrayNumbers.size - 1)
        }
        return arrayNumbers
    }

    fun makeShift(arrayNumbers: List<String>, shift: Int) : String{
        val sizeNumbers = arrayNumbers.size
        var shiftedArray = mutableListOf<String>()
        var shiftSize = shift
        if (shiftSize < 0) {
            shiftSize = - shiftSize
            shiftSize %= sizeNumbers
            shiftedArray.addAll(arrayNumbers.subList(0, shiftSize).reversed())
            shiftedArray.addAll(arrayNumbers.subList(shiftSize, sizeNumbers).reversed())
            shiftedArray = shiftedArray.reversed().toMutableList()
        } else {
            shiftSize %= sizeNumbers
            shiftSize = sizeNumbers - shiftSize
            shiftedArray.addAll(arrayNumbers.subList(0, shiftSize).reversed())
            shiftedArray.addAll(arrayNumbers.subList(shiftSize, sizeNumbers).reversed())
            shiftedArray = shiftedArray.reversed().toMutableList()
        }
        return shiftedArray.reduce{acc, string -> acc + " " + string}
    }
}