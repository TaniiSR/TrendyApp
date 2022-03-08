package com.task.trendy.data.local.localservice

import com.task.trendy.data.dtos.responsedtos.Profile
import javax.inject.Inject

class GitRepositoryLocal @Inject constructor(private val gitLocalDao: GitLocalDao) :
    GitRepoDbService {
    override suspend fun getTrendingProfiles() = gitLocalDao.getAllTProfiles()

    override suspend fun insertTrendingProfiles(profiles: List<Profile>) =
        gitLocalDao.insertAllProfiles(profiles = profiles)
}