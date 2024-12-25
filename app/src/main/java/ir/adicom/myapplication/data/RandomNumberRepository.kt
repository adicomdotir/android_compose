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

package ir.adicom.myapplication.data

import ir.adicom.myapplication.data.local.database.RandomNumber
import ir.adicom.myapplication.data.local.database.RandomNumberDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface RandomNumberRepository {
    val randomNumbers: Flow<List<Int>>

    val randomNumbersCount: Flow<Int>

    suspend fun add(value: Int)
}

class DefaultRandomNumberRepository @Inject constructor(
    private val randomNumberDao: RandomNumberDao
) : RandomNumberRepository {

    override val randomNumbers: Flow<List<Int>> =
        randomNumberDao.getRandomNumbers().map { items -> items.map { it.value } }

    override val randomNumbersCount: Flow<Int> =
        randomNumberDao.getRandomNumbersCount()

    override suspend fun add(value: Int) {
        randomNumberDao.insertRandomNumber(RandomNumber(value = value))
    }
}
