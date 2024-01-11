package ir.adicom.profile

import android.widget.EditText
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    // we are creating a variable for
    // getting a value of our text field.
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = inputValue.value.text,
            style = TextStyle(fontSize = 25.sp, color = Color.Red)
        )
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            placeholder = { Text(text = "Enter user name") },
            modifier = Modifier.padding(all = 16.dp).fillMaxWidth(),

        )
    }
}