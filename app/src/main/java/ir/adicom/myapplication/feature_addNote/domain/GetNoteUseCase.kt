package ir.adicom.myapplication.feature_addNote.domain

import ir.adicom.myapplication.core.data.repository.NotesRepositoryImpl
import ir.adicom.myapplication.core.domain.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNoteUseCase {

    private val repository: NotesRepositoryImpl = NotesRepositoryImpl.getInstance()

    suspend fun execute(id: Int): NoteModel {
        return withContext(Dispatchers.IO) {
            repository.get(id)
        }
    }

    companion object {
        private var _instance: GetNoteUseCase? = null

        fun getInstance(): GetNoteUseCase {
            if (_instance == null) {
                _instance = GetNoteUseCase()
            }

            return _instance!!
        }
    }

}