package com.example.sortingapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.sortingapplication.databinding.FragmentShiftBinding

class ShiftFragment : Fragment() {
    private var _binding: FragmentShiftBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShiftBinding.inflate(inflater, container, false)
        val view  = binding.root
        var textEdit = binding.InputTextShift
        textEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                if (!input.isEmpty()) {
                    if (!input.matches(Regex("[0-9 ]+"))) {
                        textEdit.error = "Введите, пожалуйста, массив чисел через пробел"
                    } else {
                        textEdit.error = null
                    }
                }
            }
        })
        binding.buttonShiftNext.setOnClickListener {
            if (textEdit.text.toString().trim().isEmpty() || !textEdit.text.toString().matches(Regex("[0-9 ]+"))) {
                Toast.makeText(
                    activity,
                    "Вы должны ввести массив чисел через пробел",
                    Toast.LENGTH_LONG
                ).show()
            } else if (binding.shiftSizeText.text.isEmpty()) {
                Toast.makeText(
                    activity,
                    "Вы должны ввести величину, на которую необходимо сдвинуть",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val shiftSize = binding.shiftSizeText.text.toString().toInt()
                val numbers = textEdit.text.toString()
                val action = ShiftFragmentDirections.actionShiftFragmentToShiftResultFragment(
                    numbers,
                    shiftSize
                )
                view.findNavController().navigate(action)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}