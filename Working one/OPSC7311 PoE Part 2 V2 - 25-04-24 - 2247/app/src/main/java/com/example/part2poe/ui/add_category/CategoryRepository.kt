import com.example.part2poe.ui.add_category.Category

class CategoryRepository {
    private val categories = mutableListOf<Category>()

    fun getCategories(): List<Category> {
        return categories
    }

    fun addCategory(name: String, description: String) {
        val category = Category(name, description)
        categories.add(category)
    }

    fun updateCategory(oldName: String, newName: String, newDescription: String) {
        val category = categories.find { it.name == oldName }
        category?.let {
            it.name = newName
            it.description = newDescription
        }
    }

    fun deleteCategory(name: String) {
        categories.removeAll { it.name == name }
    }

    fun printCategories() {
        println("Categories:")
        categories.forEach { category ->
            println("Name: ${category.name}, Description: ${category.description}")
        }
    }
}