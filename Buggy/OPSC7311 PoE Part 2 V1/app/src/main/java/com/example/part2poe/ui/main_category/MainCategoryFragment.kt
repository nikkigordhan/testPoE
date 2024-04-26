package com.example.part2poe.ui.main_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
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

        categroyArray()

        return root
    }

    private fun navigateToAddCategory() {
        findNavController().navigate(MainCategoryFragmentDirections.actionMaincategoryFragmentToAddcategoryFragment())
    }

    private fun categroyArray() {
        // Create instances of Category
        val category1 = Category("Alice", "C1")
        val category2 = Category("Bob", "C2")
        val category3 = Category("Charlie", "C3")

        // Store the instances in an array
        val categoryArray = arrayOf(category1, category2, category3)

        // Create an ArrayAdapter
        val adapter = ArrayAdapter(requireContext(), R.layout.fragment_main_category, R.id.textView, categoryArray)

        // Set the adapter to the ListView
        binding.listCategories.adapter = adapter

        // Access values from the array
        for (category in categoryArray) {
            println("Category Name: ${category.categoryname}, Description: ${category.description}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
