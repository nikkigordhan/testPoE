// Define the Category data class
data class Category(var name: String, var description: String)

// Create a CategoryRepository class
class CategoryRepository {

    // Mutable list to store Category objects
    private val categories = mutableListOf<Category>()

    // Function to get all categories
    fun getCategories(): List<Category> {
        return categories
    }

    // Function to add a new category
    fun addCategory(name: String, description: String) {
        val category = Category(name, description)

        // Add the new category to the list
        categories.add(category)
    }

    // Function to update a category
    fun updateCategory(oldName: String, newName: String, newDescription: String) {
        // Find the category by name and update its properties
        val category = categories.find { it.name == oldName }
        category?.let {
            it.name = newName
            it.description = newDescription
        }
    }

    // Function to delete a category
    fun deleteCategory(name: String) {
        // Remove the category from the list
        categories.removeAll { it.name == name }
    }

    // Function to print all categories
    fun printCategories() {
        println("Categories:")
        categories.forEach { category ->
            println("Name: ${category.name}, Description: ${category.description}")
        }
    }
}
