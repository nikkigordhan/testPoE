package com.example.part2poe.ui.add_project

import androidx.lifecycle.ViewModel
//import com.example.part2poe.ui.add_category.Category

class AddProjectViewModel : ViewModel() {
    private val projects = mutableListOf<Project>()

    fun addProject(project: Project) {
        projects.add(project)
    }

    fun getProjects(): List<Project> {
        return projects.toList()
    }
}