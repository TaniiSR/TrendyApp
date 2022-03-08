package com.task.trendy.data.remote.microservices.githubrepos

import com.task.trendy.data.dtos.responsedtos.GitRepos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoRetroService {
    //Get the github profiles
    @GET(GitRepositoryRemote.URL_GET_PROFILES)
    suspend fun getTopProfiles(
        @Query("q") query: String
    ): Response<GitRepos>
}