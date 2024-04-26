import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.part2poe.databinding.FragmentAddCategoryBinding
import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections

import com.example.part2poe.ui.add_category.Category

class AddCategoryFragment : Fragment() {
    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    // Array to store categories
    private val categories: MutableList<Category> = mutableListOf()

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

        btnSave.setOnClickListener()
        {
            val categoryName = editCategoryName.text.toString().trim()
            val description = editDescription.text.toString().trim()

            if (isValidInput(categoryName, description)) {
                addNewCategory(categoryName, description)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCancel.setOnClickListener()
        {
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
        // Create a new Category object and add it to the array
        val newCategory = Category(categoryName, description)
        categories.add(newCategory)

        Toast.makeText(requireContext(), "New category added successfully!", Toast.LENGTH_SHORT).show()

        clearFields(binding.etxtCategoryName, binding.etxtDescription)
        navigateToMainCategory()
    }

    private fun clearFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }

    private fun navigateToMainCategory() {
        findNavController().navigate(MainCategoryFragmentDirections.actionAddcategoryoryFragementToMaincategoryFragement())
    }
}
