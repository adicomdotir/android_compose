package ir.adicom.myapplication.feature_home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import ir.adicom.myapplication.feature_home.domain.GetNotesUseCase
import ir.adicom.myapplication.feature_home.domain.ListenNotesUseCase

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {


    @Provides
    fun provideGetNotesUseCase(repository: NotesRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    fun provideListenNotesUseCase(repository: NotesRepository): ListenNotesUseCase {
        return ListenNotesUseCase(repository)
    }


}