package com.task.trendy.domain

import com.task.trendy.base.BaseTestCase
import com.task.trendy.data.dtos.responsedtos.GitRepos
import com.task.trendy.data.local.localservice.GitRepoDbService
import com.task.trendy.data.local.localservice.GitRepositoryLocal
import com.task.trendy.data.remote.baseclient.ApiResponse
import com.task.trendy.data.remote.microservices.githubrepos.GitRepoApi
import com.task.trendy.data.remote.microservices.githubrepos.GitRepositoryRemote
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest : BaseTestCase() {
    // Subject under test
    lateinit var dataRepository: DataRepository

    // Use a fake UseCase to be injected into the DataRepository
    lateinit var remoteData: GitRepoApi
    lateinit var localData: GitRepoDbService

    @Before
    fun setUp() {
        remoteData = mockk<GitRepositoryRemote>()
        localData = mockk<GitRepositoryLocal>()
    }

    @Test
    fun `get trendy repo list success`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Success<GitRepos>> {
                every { data } returns mockk {
                    every { repos } returns listOf(mockk(), mockk())
                }
            }
            val profiles = response.data.repos ?: listOf()

            coEvery {
                localData.insertTrendingProfiles(profiles)
            } returns Unit

            coEvery {
                localData.getTrendingProfiles()
            } returns profiles

            coEvery {
                remoteData.getTopProfiles(
                    query
                )
            } returns response
            //2-Call
            dataRepository = DataRepository(remoteData, localData)
            dataRepository.getGithubProfiles(query, true)

            //3-verify
            coVerify { dataRepository.getGithubProfiles(query, true) }

        }
    }

    @Test
    fun `get trendy repo list with local success`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Success<GitRepos>> {
                every { data } returns mockk {
                    every { repos } returns listOf(mockk(), mockk())
                }
            }
            val profiles = response.data.repos ?: listOf()

            coEvery {
                localData.getTrendingProfiles()
            } returns profiles

            //2-Call
            dataRepository = DataRepository(remoteData, localData)
            val mockedCallResponse = dataRepository.getGithubProfiles(query, false)

            //3-verify
            when (mockedCallResponse) {
                is ApiResponse.Success -> {
                    Assert.assertEquals(profiles, mockedCallResponse.data.repos)
                }
            }
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}