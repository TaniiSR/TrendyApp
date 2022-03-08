package com.task.trendy.data.remote.microservices.githubrepos

import com.task.trendy.data.dtos.responsedtos.GitRepos
import com.task.trendy.data.remote.baseclient.ApiResponse
import com.task.trendy.data.remote.baseclient.BaseRepository
import javax.inject.Inject

class GitRepositoryRemote @Inject constructor(
    private val service: GitRepoRetroService
) : BaseRepository(), GitRepoApi {

    companion object {
        const val URL_GET_PROFILES = "search/repositories"
    }

    override suspend fun getTopProfiles(query: String): ApiResponse<GitRepos> {
        return executeSafely(call = {
            service.getTopProfiles(query)
        })
    }
}