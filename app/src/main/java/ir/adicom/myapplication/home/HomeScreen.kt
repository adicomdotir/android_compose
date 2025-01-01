package ir.adicom.myapplication.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import ir.adicom.myapplication.R
import ir.adicom.myapplication.Routes
import ir.adicom.myapplication.models.NoteModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    newNote: String? = null,
    navigateNext: (String) -> Unit
) {
    val notes = viewModel.notes

    LaunchedEffect(key1 = true) {
        if (newNote.isNullOrEmpty()) return@LaunchedEffect

        val newNoteObj = Gson().fromJson(newNote, NoteModel::class.java)
        viewModel.saveNote(newNoteObj)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
                    .padding(
                        start = 50.dp,
                        top = 20.dp,
                        bottom = 20.dp,
                    )
            ) {
                Text(
                    text = "Notes App",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateNext(Routes.ADD_NOTE)
                },
                containerColor = MaterialTheme.colorScheme.tertiary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    color = Color.LightGray
                ),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(
                vertical = 18.dp
            )
        ) {
            items(notes.size) { index ->
                val note = notes[index]

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            viewModel.listItemOnClick(note.id)
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 14.dp,
                        )
                ) {
                    Text(
                        text = note.title,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = note.description,
                    )
                }
            }
        }
    }
}