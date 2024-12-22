package ir.adicom.myapplication.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

const val CellWidthCount: Int = 10
val SurfaceBgColor: Color = Color(0xFFFFF4F4)

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.background(Color.Red),
        topBar = { AddAppBar() },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding)
                    .background(SurfaceBgColor),
            ) {
                val context = LocalContext.current
                val displayMetrics = context.resources.displayMetrics

                // Width and height of screen
                val width = displayMetrics.widthPixels
                val height = displayMetrics.heightPixels

                // Device density
                val density = displayMetrics.density
                val letterWidthSize = width / density / CellWidthCount

                Column {
                    Text("${width / density}")
                    Text("$height")
                    Text("$density")
                    Spacer(modifier =  Modifier.weight(1.0f))
                    Row {
                        LetterCell(letterWidthSize, "A")
                        LetterCell(letterWidthSize, "B")
                        LetterCell(letterWidthSize, "C")
                        LetterCell(letterWidthSize, "D")
                        LetterCell(letterWidthSize, "A")
                        LetterCell(letterWidthSize, "B")
                        LetterCell(letterWidthSize, "E")
                        LetterCell(letterWidthSize, "C")
                        LetterCell(letterWidthSize, "D")
                        LetterCell(letterWidthSize, "E")
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        LetterCell(letterWidthSize, "A")
                        LetterCell(letterWidthSize, "B")
                        LetterCell(letterWidthSize, "C")
                        LetterCell(letterWidthSize, "D")
                        LetterCell(letterWidthSize, "E")
                        LetterCell(letterWidthSize, "A")
                        LetterCell(letterWidthSize, "B")
                        LetterCell(letterWidthSize, "C")
                        LetterCell(letterWidthSize, "D")
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    )  {
                        LetterCell(letterWidthSize, "A")
                        LetterCell(letterWidthSize, "B")
                        LetterCell(letterWidthSize, "C")
                        LetterCell(letterWidthSize, "D")
                        LetterCell(letterWidthSize, "E")
                        LetterCell(letterWidthSize, "A")
                        LetterCell(letterWidthSize, "B")
                        LetterCell(letterWidthSize, "C")
                    }
                    Spacer(modifier =  Modifier.size(48.dp))
                }
            }
        }
    )
}

@Composable
private fun LetterCell(width: Float, letter: String) {
    Box(
        modifier = Modifier
            .size(width.dp, width.dp)
            .padding(4.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter, color = Color.Gray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "profile picture",
                )
                Text("John Doe")
            }
        },
        navigationIcon = {
            IconButton({}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "menu items"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "video call",
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "phone call",
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "more options",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}
