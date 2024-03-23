package com.example.sortingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sortingapplication.database.OperationType
import com.example.sortingapplication.database.SortingApplicationDatabase
import com.example.sortingapplication.database.SortingApplicationRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SortingApplicationRepository.initialize(this)
        setContentView(R.layout.activity_main)
    }
}