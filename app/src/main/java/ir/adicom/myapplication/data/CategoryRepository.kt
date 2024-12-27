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

import ir.adicom.myapplication.data.local.database.Category
import ir.adicom.myapplication.data.local.database.CategoryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoryRepository {
    val categories: Flow<List<Category>>
    suspend fun add(item: Category)
    suspend fun delete(item: Category)
    suspend fun update(item: Category)
    suspend fun getCategoryById(id: Int): Flow<Category>
}

class DefaultCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override val categories: Flow<List<Category>> =
        categoryDao.getCategories()

    override suspend fun add(item: Category) {
        categoryDao.insertCategory(item)
    }

    override suspend fun delete(item: Category) {
        categoryDao.deleteCategory(item)
    }

    override suspend fun update(item: Category) {
        categoryDao.updateCategory(item)
    }

    override suspend fun getCategoryById(id: Int): Flow<Category> {
        return categoryDao.getCategoryById(id)
    }
}
