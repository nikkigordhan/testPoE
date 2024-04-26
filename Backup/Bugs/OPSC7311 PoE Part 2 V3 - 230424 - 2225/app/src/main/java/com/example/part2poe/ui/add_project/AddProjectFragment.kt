package com.example.part2poe.ui.add_project

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.part2poe.R
import com.example.part2poe.databinding.FragmentAddCategoryBinding
import com.example.part2poe.databinding.FragmentAddProjectBinding
import com.example.part2poe.ui.add_category.AddCategoryViewModel
import com.example.part2poe.ui.add_category.Category
import com.example.part2poe.ui.add_project.Project.Project
import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections
import com.example.part2poe.ui.main_project.MainProjectFragment

class AddProjectFragment : Fragment() {

    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!

    private val addCategoryViewModel: AddProjectViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AddProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val editProjectName = binding.etxtProjecName
        val editMinGoal = binding.etxtMinGoal
        val editMaxGoal = binding.etxtMaxGoal
        val editCost = binding.etxtCost
        val editCategory = binding.dpCategory
        val editCalendar = binding.calendarView
        val btnSave = binding.btnSave
        val btnCancel = binding.btnCancel

        btnSave.setOnClickListener()
        {
            val projectName = editProjectName.text.toString().trim()
            val minGoal = editMinGoal.text.toString().trim()
            val maxGoal = editMaxGoal.text.toString().trim()
            val cost = editCost.text.toString().trim()
            val category = editCategory.toString().trim()
            val calendar = editCalendar.toString().trim()

            if (isValidInput(projectName, minGoal, maxGoal, cost,  category, calendar )) {
                addnewProject(projectName, minGoal, maxGoal, cost,  category, calendar)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }

            btnCancel.setOnClickListener()
            {
                clearFields(editProjectName, editMinGoal, editMaxGoal,editCost, editCategory, editCalendar,  )
            }
            return root
        }
        private fun isValidInput(
            projectName: String,
            category: String,
            calendar: String,
            minGoal: String,
            maxGoal: String,
            cost: String

        ): Boolean {
            return projectName.isNotEmpty() && category.isNotEmpty() && calendar.isNotEmpty() && minGoal.isNotEmpty() && minGoal.isNotEmpty() && maxGoal.isNotEmpty() && cost.isNotEmpty()
        }

        private fun addnewProject(
            projectName: String,
            category: String,
            calendar: String,
            minGoal: String,
            maxGoal: String,
            cost: String
        ) {
            // Create a new Category object and add it to the ViewModel
            val newProject = Project(projectName, minGoal, maxGoal, cost,  category, calendar)
            addProjectView.addProject(newProject)

            Toast.makeText(requireContext(), "New project added successfully!", Toast.LENGTH_SHORT).show()

            clearFields(binding.etxtProjecName, binding.etxtMinGoal, binding.etxtMaxGoal, binding.etxtCost, binding.dpCategory, binding.calendarView )
            navigateToMainProject()
        }
        private fun clearFields(vararg editTexts: EditText) {
            for (editText in editTexts) {
                editText.text.clear()
            }
        }

        private fun navigateToMainCategory() {
            findNavController().navigate(MainCategoryFragmentDirections.)
        }

}