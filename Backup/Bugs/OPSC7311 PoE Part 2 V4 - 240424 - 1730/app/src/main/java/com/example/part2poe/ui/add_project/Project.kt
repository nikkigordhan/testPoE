import com.example.part2poe.ui.add_category.Category
import com.example.part2poe.ui.add_category.AddCategoryViewModel

data class Project(
    val projectName: String,
    val minGoal: String,
    val maxGoal: String,
    val cost: String,
    val calendar: String,
    val category: String
) {
    // Sample method to retrieve data
    fun getData(viewModel: AddCategoryViewModel): List<Category> {
        // Replace this with your actual data retrieval logic
        return viewModel.getCategories()
    }
}
