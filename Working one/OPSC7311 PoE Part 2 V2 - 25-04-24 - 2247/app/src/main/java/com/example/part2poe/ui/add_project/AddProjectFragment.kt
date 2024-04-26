
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
import com.example.part2poe.databinding.FragmentAddProjectBinding


import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections

class AddProjectFragment : Fragment() {

    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!

    private lateinit var spinner: Spinner
    private lateinit var categoryRepository: CategoryRepository
    private lateinit var viewModel: AddProjectViewModel // Initialize the view model

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

        // Initialize the view model
        viewModel = ViewModelProvider(this).get(AddProjectViewModel::class.java)

        // Populate spinner with categories
        viewModel.populateSpinner(requireContext(), binding.dpCategory)

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
        // Your project creation logic here

        Toast.makeText(requireContext(), "New project added successfully!", Toast.LENGTH_SHORT)
            .show()

        clearFields(binding.etxtProjecName, binding.etxtMinGoal, binding.etxtMaxGoal, binding.etxtCost)
       // navigateToMainProject()
    }

    private fun clearFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }

   /* private fun navigateToMainProject() {
        findNavController().navigate(MainCategoryFragmentDirections.actionAddprojectFragmentToMainprojectFragment())
    }*/


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
