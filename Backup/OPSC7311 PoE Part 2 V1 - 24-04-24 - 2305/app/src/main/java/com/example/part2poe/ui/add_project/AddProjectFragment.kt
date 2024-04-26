package com.example.part2poe.ui.add_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.part2poe.databinding.FragmentAddProjectBinding
import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections



class AddProjectFragment : Fragment() {

    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!

    // Array to store categories
    //private val categories: MutableList<Category> = mutableListOf()

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
       // val editCategory = binding.dpCategory
        val editCalendar = binding.calendarView
        val btnSave = binding.btnSave
        val btnCancel = binding.btnCancel

        btnSave.setOnClickListener()
        {
            val projectName = editProjectName.text.toString().trim()
            val minGoal = editMinGoal.text.toString().trim()
            val maxGoal = editMaxGoal.text.toString().trim()
            val cost = editCost.text.toString().trim()
           // val category = editCategory.toString().trim()
            val calendar = editCalendar.toString().trim()


            if (isValidInput(projectName, minGoal, maxGoal, cost, calendar)) {
                addnewProject(projectName, minGoal, maxGoal, cost, calendar)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCancel.setOnClickListener()
        {
            clearFields(editProjectName, editMinGoal,editMaxGoal,editCost)
        }
        return root
    }
        private fun isValidInput(
            projectName: String,
           // category: String,
            calendar: String,
            minGoal: String,
            maxGoal: String,
            cost: String

        ): Boolean {
            return projectName.isNotEmpty()  && calendar.isNotEmpty() && minGoal.isNotEmpty() && minGoal.isNotEmpty() && maxGoal.isNotEmpty() && cost.isNotEmpty()
        }

        private fun addnewProject(
            projectName: String,
           // category: String,
            calendar: String,
            minGoal: String,
            maxGoal: String,
            cost: String
        ) {
            // Create a new Project object and add it to the ViewModel
            val newProject = Project(projectName, minGoal,maxGoal,cost,calendar)
            addProjectViewModel.addProject(newProject)

            Toast.makeText(requireContext(), "New project added successfully!", Toast.LENGTH_SHORT)
                .show()

            clearFields(
                binding.etxtProjecName,
                binding.etxtMinGoal,
                binding.etxtMaxGoal,
                binding.etxtCost,

               // binding.dpCategory,

            )
            navigateToMainProject()
        }

        private fun clearFields(vararg editTexts: EditText) {
            for (editText in editTexts) {
                editText.text.clear()
            }
        }

   /*fun populateSpinner(context: Context, spinner: Spinner) {
        // Step 3: Set the adapter to the Spinner
        val adapter = ArrayAdapter(context, R.layout., categories.map { it.name })
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }*/

        private fun navigateToMainProject() {
            findNavController().navigate(MainCategoryFragmentDirections.actionAddprojectFragmentToMainprojectFragment())
        }

    }
