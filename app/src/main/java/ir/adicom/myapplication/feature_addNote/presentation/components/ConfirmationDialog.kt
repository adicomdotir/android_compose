package ir.adicom.myapplication.feature_addNote.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationDialog(
    modifier: Modifier = Modifier,
    dismissButton: () -> Unit,
    confirmButton: () -> Unit,
) {

    AlertDialog(
        modifier = modifier,
        onDismissRequest = { dismissButton() },
        title = {
            Text(
                text = "Alert",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
        },
        text = {
            Text(
                text = "Do you really want to delete this note ?",
            )
        },
        dismissButton = {
            Button(
                onClick = dismissButton
            ) {
                Text("No")
            }
        },
        confirmButton = {
            Button(
                onClick = confirmButton,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Yes")
            }
        }
    )
}