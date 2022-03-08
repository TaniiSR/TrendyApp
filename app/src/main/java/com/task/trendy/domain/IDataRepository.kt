package com.task.trendy.domain

import com.task.trendy.data.dtos.responsedtos.GitRepos
import com.task.trendy.data.remote.baseclient.ApiResponse

interface IDataRepository {
    suspend fun getGithubProfiles(
        query: String,
        isRefresh: Boolean
    ): ApiResponse<GitRepos>
}