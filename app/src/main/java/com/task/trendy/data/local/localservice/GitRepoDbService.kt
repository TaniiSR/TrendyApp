package com.task.trendy.data.local.localservice

import com.task.trendy.data.dtos.responsedtos.Profile

interface GitRepoDbService {
    suspend fun getTrendingProfiles(): List<Profile>
    suspend fun insertTrendingProfiles(profiles: List<Profile>)
}