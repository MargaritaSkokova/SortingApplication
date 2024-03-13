package com.example.sortingapplication

import androidx.lifecycle.ViewModel

class SortResultViewModel: ViewModel() {
    fun sort(array: String, isAsc: Boolean) : String {
        var numbers = array
        var arrayNumbersTemp = numbers.split("\\s+".toRegex())
        if (arrayNumbersTemp[arrayNumbersTemp.size - 1] == "" || arrayNumbersTemp[arrayNumbersTemp.size - 1] == " ") {
            arrayNumbersTemp = arrayNumbersTemp.subList(0, arrayNumbersTemp.size - 1)
        }
        var arrayNumbers = arrayNumbersTemp.map { it.toInt() }
        arrayNumbers = arrayNumbers.sortedBy { it }
        numbers = arrayNumbers.map { it.toString() }.reduce { acc, string -> acc + " " + string }
        if (!isAsc) {
            numbers = numbers.reversed()
        }
        return numbers
    }
}