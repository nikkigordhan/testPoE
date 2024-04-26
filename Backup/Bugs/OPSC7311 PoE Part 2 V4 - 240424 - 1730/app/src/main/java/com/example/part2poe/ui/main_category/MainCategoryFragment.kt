package com.example.part2poe.ui.main_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.part2poe.databinding.FragmentAddCategoryBinding
import com.example.part2poe.databinding.FragmentMainCategoryBinding
import com.example.part2poe.ui.main_category.MainCategoryFragmentDirections
import com.example.part2poe.ui.main_category.MainCategoryViewModel

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

        val mainCategoryViewModel = ViewModelProvider(this).get(MainCategoryViewModel::class.java)

        // Find the Add New Category button and set a click listener
        val addCategoryButton: Button = binding.btnAddNewCategory
        addCategoryButton.setOnClickListener {
            navigateToAddCategory()
        }

        return root
    }

    private fun navigateToAddCategory() {
        findNavController().navigate(MainCategoryFragmentDirections.actionMaincategoryFargmentToAddcategoryFragment())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
