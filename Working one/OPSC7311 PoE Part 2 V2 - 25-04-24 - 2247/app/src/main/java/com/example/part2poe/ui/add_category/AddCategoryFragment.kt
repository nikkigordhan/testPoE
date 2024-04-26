package com.example.part2poe.ui.add_category

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.part2poe.R
import com.example.part2poe.databinding.FragmentAddCategoryBinding
import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections

class AddCategoryFragment : Fragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var txtCategories: TextView  // Declare the TextView

    // Declare the shared CategoryViewModel
    private val categoryViewModel: CategoryViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val editCategoryName = binding.etxtCategoryName
        val editDescription = binding.etxtDescription
        val btnSave = binding.btnSave
        val btnCancel = binding.btnCancel

        txtCategories = binding.txtCategories  // Initialize the TextView

        btnSave.setOnClickListener {
            val categoryName = editCategoryName.text.toString().trim()
            val description = editDescription.text.toString().trim()

            if (isValidInput(categoryName, description)) {
                addNewCategory(categoryName, description)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        printCategories()

        btnCancel.setOnClickListener {
            clearFields(editCategoryName, editDescription)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isValidInput(
        categoryName: String,
        description: String
    ): Boolean {
        return categoryName.isNotEmpty() && description.isNotEmpty()
    }

    private fun addNewCategory(
        categoryName: String,
        description: String
    ) {
        // Create a new Category object
        val newCategory = Category(categoryName, description)

        // Add the new category to the shared ViewModel
        categoryViewModel.categories.add(newCategory)

        Toast.makeText(requireContext(), "New category added successfully!", Toast.LENGTH_SHORT)
            .show()

        // Print the categories
        printCategories()

        // Clear the fields
        clearFields(binding.etxtCategoryName, binding.etxtDescription)
    }

    private fun clearFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }

   /* private fun printCategories() {
        val categoriesText = StringBuilder("Categories:\n")
        categoryViewModel.categories.forEach { category ->
            categoriesText.append("Name: ${category.name}, Description: ${category.description}\n")
        }
        txtCategories.text = categoriesText.toString()  // Update TextView text
    }*/

    private fun printCategories() {
        val categoriesText = StringBuilder("Categories:\n")
        for (category in categoryViewModel.categories) {
            categoriesText.append("Name: ${category.name}, Description: ${category.description}\n")
        }
        txtCategories.text = categoriesText.toString()  // Update TextView text
    }





    private fun navigateToMainCategory() {
        findNavController().navigate(MainCategoryFragmentDirections.actionAddcategoryFragmentToMaincategoryFragment())
    }
}
