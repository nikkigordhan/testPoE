package com.example.part2poe.ui.add_category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.part2poe.R

class CategoryAdapter(
    context: Context,
    private val categories: List<Category>
) : ArrayAdapter<Category>(context, R.layout.fragment_add_category, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                R.layout.fragment_add_category,
                parent,
                false
            )
        }

        val currentCategory = getItem(position)

        val nameTextView = listItemView!!.findViewById<TextView>(R.id.etxtCategoryName)
        nameTextView.text = "Name: ${currentCategory?.name}"

        val descriptionTextView = listItemView.findViewById<TextView>(R.id.etxtDescription)
        descriptionTextView.text = "Description: ${currentCategory?.description}"

        return listItemView
    }
}
