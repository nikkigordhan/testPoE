import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModel

class AddProjectViewModel : ViewModel() {

    fun populateSpinner(context: Context, spinner: Spinner) {
        // Initialize CategoryRepository
        val categoryRepository = CategoryRepository()

        // Get categories from CategoryRepository
        val categories = categoryRepository.getCategories()

        // Populate Spinner with category names


        val categoryNames = categories.map { it.name }

        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, categoryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}
