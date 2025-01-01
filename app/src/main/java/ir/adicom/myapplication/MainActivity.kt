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

package ir.adicom.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ir.adicom.myapplication.addNote.AddNoteScreen
import ir.adicom.myapplication.home.HomeScreen
import ir.adicom.myapplication.ui.theme.MyApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME
                    ) {
                        composable(Routes.HOME) {
                            val newNoteJsonStr =
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.getStateFlow(
                                        "new_note",
                                        ""
                                    )?.collectAsState()
                            HomeScreen(
                                newNote = newNoteJsonStr?.value,
                                navigateNext = { route ->
                                    navController.navigate(route)
                                }
                            )
                        }
                        composable(Routes.ADD_NOTE) {
                            AddNoteScreen(navigateBack = { newNote ->
                                val jsonStr = Gson().toJson(newNote)
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set(
                                        "new_note",
                                        jsonStr
                                    )
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}
