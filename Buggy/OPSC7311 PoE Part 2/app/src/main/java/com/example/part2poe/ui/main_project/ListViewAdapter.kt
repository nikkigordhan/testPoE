package com.example.part2poe.ui.main_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.part2poe.R

class ListViewAdapter(private val context: Context, private val values: Array<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): Any {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.fragment_main_project, parent, false)
        val textView = view.findViewById<TextView>(R.id.txtListOfCategories)
        textView.text = values[position]
        return view
    }
}
