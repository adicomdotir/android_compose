package ir.adicom.myapplication.feature_home.domain

import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListenNotesUseCase constructor(
    private val repository: NotesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun execute(): Flow<NotesEvent> {

        return channelFlow {

            withContext(ioDispatcher) {

                launch {
                    repository.newNoteInsertionListener.collect { newNote ->
                        send(NotesEvent.Insert(newNote))
                    }
                }

                launch {
                    repository.updateNoteListener.collect { updatedNote ->
                        send(NotesEvent.Update(updatedNote))
                    }
                }

                launch {
                    repository.deleteNoteListener.collect { id ->
                        send(NotesEvent.Delete(id))
                    }
                }

            }
        }
    }
}


sealed interface NotesEvent {
    data class Insert(val value: NoteModel) : NotesEvent
    data class Update(val value: NoteModel) : NotesEvent
    data class Delete(val value: Int) : NotesEvent
}