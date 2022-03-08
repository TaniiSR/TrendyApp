package com.task.trendy.di

import com.task.trendy.data.local.localservice.GitRepoDbService
import com.task.trendy.data.local.localservice.GitRepositoryLocal
import com.task.trendy.data.remote.microservices.githubrepos.GitRepoApi
import com.task.trendy.data.remote.microservices.githubrepos.GitRepositoryRemote
import com.task.trendy.domain.DataRepository
import com.task.trendy.domain.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideGitRemoteRepository(gitRepositoryRemote: GitRepositoryRemote): GitRepoApi

    @Binds
    @Singleton
    abstract fun provideGitLocalRepository(gitRepositoryLocal: GitRepositoryLocal): GitRepoDbService

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): IDataRepository
}