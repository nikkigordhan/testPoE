package com.example.part2poe.ui.main_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.part2poe.R
import com.example.part2poe.databinding.FragmentMainCategoryBinding
import com.example.part2poe.ui.add_category.Category



class MainCategoryFragment : Fragment() {

    private var _binding: FragmentMainCategoryBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Find the Add New Category button and set a click listener
        val addCategoryButton: Button = binding.btnAddNewCategory
        addCategoryButton.setOnClickListener {
            navigateToAddCategory()
        }




        return root
    }

    private fun navigateToAddCategory() {
        findNavController().navigate(MainCategoryFragmentDirections.actionMaincategoryFragmentToAddcategroyFragment())
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
