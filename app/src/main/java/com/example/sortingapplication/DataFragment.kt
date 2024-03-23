package com.example.sortingapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.example.sortingapplication.database.OperationType
import com.example.sortingapplication.database.SortingApplicationDatabase
import com.example.sortingapplication.database.SortingApplicationRepository
import com.example.sortingapplication.databinding.FragmentDataBinding

class DataFragment : Fragment() {

    private lateinit var viewModel: DataViewModel
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        val textDataView = binding.dataOutput
        viewModel.outputDataTypes(textDataView)
        return view
    }

}