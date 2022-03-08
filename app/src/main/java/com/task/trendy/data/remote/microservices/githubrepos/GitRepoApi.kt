package com.task.trendy.data.remote.microservices.githubrepos

import com.task.trendy.data.dtos.responsedtos.GitRepos
import com.task.trendy.data.remote.baseclient.ApiResponse


interface GitRepoApi {
    suspend fun getTopProfiles(query: String): ApiResponse<GitRepos>
}