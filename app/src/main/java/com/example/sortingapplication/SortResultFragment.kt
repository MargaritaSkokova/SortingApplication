package com.example.sortingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sortingapplication.databinding.FragmentSortResultBinding
import androidx.lifecycle.ViewModelProvider
import com.example.sortingapplication.database.SortingApplicationRepository
import java.util.UUID

class SortResultFragment : Fragment() {
    var _binding: FragmentSortResultBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: SortResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSortResultBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(SortResultViewModel::class.java)

        var numbers = SortResultFragmentArgs.fromBundle(requireArguments()).numbers

        val db = SortingApplicationRepository.get();
        val arrayBefore = numbers

        val sortedArray = binding.sortedArray
        val isAsc = SortResultFragmentArgs.fromBundle(requireArguments()).isAsc
        numbers = viewModel.sort(numbers, isAsc)
        sortedArray.text = numbers

        val arrayAfter = numbers
        val idType = db.getTypeIdByName("Sort")
        db.insertOperation(idType, arrayBefore, arrayAfter);

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}