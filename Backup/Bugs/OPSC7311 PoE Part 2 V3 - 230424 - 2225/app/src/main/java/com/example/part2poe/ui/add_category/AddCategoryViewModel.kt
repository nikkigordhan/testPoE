package com.example.part2poe.ui.add_category

import androidx.lifecycle.ViewModel

class AddCategoryViewModel : ViewModel() {
    private val categories = mutableListOf<Category>()

    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun getCategories(): List<Category> {
        return categories.toList()
    }
}
