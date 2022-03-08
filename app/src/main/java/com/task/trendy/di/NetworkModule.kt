package com.task.trendy.di

import com.task.trendy.data.remote.baseclient.RetroNetwork
import com.task.trendy.data.remote.microservices.githubrepos.GitRepoRetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesGitRepoRetroService(): GitRepoRetroService =
        RetroNetwork().createService(GitRepoRetroService::class.java)
}