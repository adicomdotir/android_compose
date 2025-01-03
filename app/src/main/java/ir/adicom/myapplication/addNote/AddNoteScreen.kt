package ir.adicom.myapplication.addNote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.adicom.myapplication.R
import ir.adicom.myapplication.models.NoteModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigateBack: (NoteModel) -> Unit,
) {
    val title = viewModel.title.collectAsState()
    val description = viewModel.description.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is AddNoteViewModel.Event.NavigateBack -> navigateBack(it.note)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        viewModel.backIconOnClick()
                    },
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
        }

        TextField(
            title.value,
            onValueChange = {
                viewModel.titleOnValueChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Enter Title") },
            textStyle = LocalTextStyle.current.copy(fontSize = 20.sp)
        )

        TextField(
            description.value,
            onValueChange = {
                viewModel.descriptionOnValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = { Text(text = "Enter Description") }
        )
    }
}