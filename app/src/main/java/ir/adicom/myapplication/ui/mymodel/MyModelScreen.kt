/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.adicom.myapplication.ui.mymodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import timber.log.Timber

@Composable
fun MyModelScreen(
    modifier: Modifier = Modifier,
    viewModel: MyModelViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val items by viewModel.uiState.collectAsState()
    if (items is MyModelUiState.Success) {
        MyModelScreen(
            items = (items as MyModelUiState.Success).data,
            onSave = viewModel::addMyModel,
            modifier = modifier,
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyModelScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Main") })
        },
    ) { innerPadding ->
        Timber.tag("TAG").e(innerPadding.toString())
        Column(modifier.padding(innerPadding)) {
            var nameMyModel by remember { mutableStateOf("Compose") }
            Button(
                onClick = {
                    navController.navigate("add-category")
                },
            ) {
                Text("Go To RandomNumber Screen")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = nameMyModel,
                    onValueChange = { nameMyModel = it }
                )

                Button(modifier = Modifier.width(96.dp), onClick = { onSave(nameMyModel) }) {
                    Text("Save")
                }
            }
            items.forEach {
                Text("Saved item: $it")
            }
        }
    }
}
