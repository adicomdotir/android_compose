package ir.adicom.myapplication.feature_addNote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import ir.adicom.myapplication.feature_addNote.domain.AddNoteUseCase
import ir.adicom.myapplication.feature_addNote.domain.DeleteNoteUseCase
import ir.adicom.myapplication.feature_addNote.domain.GetNoteUseCase

@Module
@InstallIn(ViewModelComponent::class)
object AddNoteModule {
    @Provides
    fun provideGetNoteUseCase(repository: NotesRepository) : GetNoteUseCase {
        return GetNoteUseCase(repository)
    }

    @Provides
    fun provideAddNoteUseCase(repository: NotesRepository) : AddNoteUseCase {
        return AddNoteUseCase(repository)
    }

    @Provides
    fun provideDeleteNoteUseCase(repository: NotesRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }
}
