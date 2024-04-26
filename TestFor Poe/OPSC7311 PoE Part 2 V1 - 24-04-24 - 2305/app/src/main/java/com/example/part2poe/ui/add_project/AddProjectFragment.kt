package com.example.part2poe.ui.add_project

import CategoryRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.part2poe.R
import com.example.part2poe.databinding.FragmentAddProjectBinding
import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections

class AddProjectFragment : Fragment() {

    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!

    private lateinit var spinner: Spinner
    private val categoryRepository = CategoryRepository()

    private val addProjectViewModel: AddProjectViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AddProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val editProjectName = binding.etxtProjecName
        val editMinGoal = binding.etxtMinGoal
        val editMaxGoal = binding.etxtMaxGoal
        val editCost = binding.etxtCost
        val editCalendar = binding.calendarView
        val btnSave = binding.btnSave
        val btnCancel = binding.btnCancel

        btnSave.setOnClickListener {
            val projectName = editProjectName.text.toString().trim()
            val minGoal = editMinGoal.text.toString().trim()
            val maxGoal = editMaxGoal.text.toString().trim()
            val cost = editCost.text.toString().trim()
            val calendar = editCalendar.date.toString()  // Use date instead of toString()
            val category = spinner.selectedItem.toString()  // Get selected item from Spinner

            if (isValidInput(projectName, minGoal, maxGoal, cost, calendar, category)) {
                addNewProject(projectName, minGoal, maxGoal, cost, calendar, category)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCancel.setOnClickListener {
            clearFields(editProjectName, editMinGoal, editMaxGoal, editCost)
        }

        // Initialize the spinner
        spinner = binding.dpCategory

        return root
    }

    private fun isValidInput(
        projectName: String,
        calendar: String,
        minGoal: String,
        maxGoal: String,
        cost: String,
        category: String
    ): Boolean {
        return projectName.isNotEmpty() && calendar.isNotEmpty() && minGoal.isNotEmpty() &&
                maxGoal.isNotEmpty() && cost.isNotEmpty() && category.isNotEmpty()
    }

    private fun addNewProject(
        projectName: String,
        calendar: String,
        minGoal: String,
        maxGoal: String,
        cost: String,
        category: String
    ) {
        val newProject = Project(projectName, minGoal, maxGoal, cost, calendar)
        addProjectViewModel.addProject(newProject)

        Toast.makeText(requireContext(), "New project added successfully!", Toast.LENGTH_SHORT)
            .show()

        clearFields(binding.etxtProjecName, binding.etxtMinGoal, binding.etxtMaxGoal, binding.etxtCost)
        navigateToMainProject()
    }

    private fun clearFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }

    private fun navigateToMainProject() {
        findNavController().navigate(MainCategoryFragmentDirections.actionAddprojectFragmentToMainprojectFragment())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get categories from CategoryRepository
        val categories = categoryRepository.getCategories()

        // Create an ArrayAdapter to populate the Spinner
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories.map { it.name } // Assuming Category has a 'name' property
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter to the Spinner
        spinner.adapter = adapter
    }
}
