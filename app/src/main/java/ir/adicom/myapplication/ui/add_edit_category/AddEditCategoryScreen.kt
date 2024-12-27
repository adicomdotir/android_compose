package ir.adicom.myapplication.ui.add_edit_category

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import java.util.Locale

@Composable
fun AddEditCategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditCategoryViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    AddEditCategoryScreen(
        onSave = viewModel::addCategory,
        modifier = modifier,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddEditCategoryScreen(
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Category") })
        },
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {

            var titleText by remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Title")
                Spacer(modifier = Modifier.width(24.dp))
                OutlinedTextField(
                    value = titleText,
                    onValueChange = { titleText = it },
                    placeholder = {
                        Text("Category Title")
                    },
                )
            }

            Button(
                modifier = Modifier.width(96.dp),
                onClick = {
                    if (titleText.isNotEmpty() && isValidText(titleText)) {
                        onSave(titleText)
                    } else {
                        Toast.makeText(
                            context,
                            "Please enter category title".uppercase(Locale.ENGLISH),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            ) {
                Text("Save")
            }
        }
    }
}

fun isValidText(text: String): Boolean {
    return text.length > 3
}