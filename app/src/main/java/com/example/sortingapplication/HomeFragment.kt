package com.example.sortingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sortingapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.sortButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_sortFragment)
        }
        binding.shiftButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_shiftFragment)
        }
        binding.dataButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_dataFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}