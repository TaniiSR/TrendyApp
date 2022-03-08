package com.task.trendy.domain

import com.task.trendy.data.dtos.responsedtos.GitRepos
import com.task.trendy.data.local.localservice.GitRepoDbService
import com.task.trendy.data.remote.baseclient.ApiResponse
import com.task.trendy.data.remote.microservices.githubrepos.GitRepoApi
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteRepository: GitRepoApi,
    private val localRepository: GitRepoDbService
) : IDataRepository {
    override suspend fun getGithubProfiles(
        query: String,
        isRefresh: Boolean
    ): ApiResponse<GitRepos> {
        val repos = localRepository.getTrendingProfiles()
        return when {
            !isRefresh && repos.isNotEmpty() -> {
                ApiResponse.Success(
                    200,
                    GitRepos(repos = repos)
                )
            }
            else -> {
                val response = remoteRepository.getTopProfiles(query)
                if (response is ApiResponse.Success) {
                    response.data.repos?.let { localRepository.insertTrendingProfiles(it) }
                }
                response
            }
        }
    }
}