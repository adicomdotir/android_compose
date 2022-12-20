package ir.adicom.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.adicom.person.model.Person

@Composable
fun ListScreen() {
    var persons by rememberSaveable {
        mutableStateOf(
            listOf(
                Person("aaa", false),
                Person("bbb", false),
                Person("ccc", false),
                Person("ddd", false),
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                "Title"
            )
        }
        items(items = persons) {
            NameItem(
                person = it,
                onPersonCheckedChange = {
                    persons = persons.map { person ->
                        if (person.name == it) {
                            person.copy(isSelected = !person.isSelected)
                        } else {
                            person
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NameItem(
    person: Person,
    onPersonCheckedChange: (personName: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = person.isSelected,
                onCheckedChange = {
                    onPersonCheckedChange(person.name)
                }
            )
            Text(text = person.name)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Divider()
    }
}