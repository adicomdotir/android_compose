package ir.adicom.myapplication.ui.random_number

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RandomNumberScreen(modifier: Modifier = Modifier, viewModel: RandomNumberViewModel = hiltViewModel()) {
    val items by viewModel.uiState.collectAsState()
    val count by viewModel.uiCountState.collectAsState()
    if (items is RandomNumberUiState.Success) {
        RandomNumberScreen(
            items = (items as RandomNumberUiState.Success).data,
            onSave = viewModel::addRandomNumber,
            modifier = modifier,
            count = count
        )
    }
}

@Composable
internal fun RandomNumberScreen(
    items: List<Int>,
    onSave: (value: Int) -> Unit,
    modifier: Modifier = Modifier,
    count: Int
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(modifier = Modifier.width(96.dp), onClick = { onSave(0) }) {
                Text("Add")
            }
        }
        Text(
            "$count",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
        )
        items.forEach {
            Text("Saved item: $it")
        }
    }
}
