package ir.adicom.myapplication.feature_addNote.domain

import ir.adicom.myapplication.core.data.repository.NotesRepositoryImpl
import ir.adicom.myapplication.core.domain.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddNoteUseCase {

    private val repository: NotesRepositoryImpl = NotesRepositoryImpl.getInstance()

    suspend fun execute(noteModel: NoteModel) {

        withContext(Dispatchers.IO) {

            if (noteModel.id == -1) {
                repository.insert(noteModel)
            } else {
                repository.update(noteModel)
            }
        }
    }


    companion object {
        private var _instance: AddNoteUseCase? = null

        fun getInstance(): AddNoteUseCase {
            if (_instance == null) {
                _instance = AddNoteUseCase()
            }

            return _instance!!
        }
    }

}