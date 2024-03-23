package com.example.sortingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sortingapplication.databinding.FragmentShiftResultBinding
import androidx.lifecycle.ViewModelProvider
import com.example.sortingapplication.database.SortingApplicationRepository
import java.util.UUID

class ShiftResultFragment : Fragment() {override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentShiftResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShiftResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShiftResultBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        viewModel = ViewModelProvider(this).get(ShiftResultViewModel::class.java)

        var numbers = ShiftResultFragmentArgs.fromBundle(requireArguments()).numbers

        val db = SortingApplicationRepository.get();
        val arrayBefore = numbers

        val shiftSize = ShiftResultFragmentArgs.fromBundle(requireArguments()).shiftSize
        numbers = viewModel.makeShift(viewModel.stringToArray(numbers), shiftSize)

        val arrayAfter = numbers
        db.insertOperation(db.getTypeIdByName("Shift"), arrayBefore, arrayAfter);

        val shiftOutput = binding.shiftedArray
        shiftOutput.text = numbers
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}