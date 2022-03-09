package com.task.trendy.ui.dashaboard

import com.task.trendy.base.BaseTestCase
import com.task.trendy.base.getOrAwaitValue
import com.task.trendy.data.dtos.responsedtos.GitRepos
import com.task.trendy.data.remote.baseclient.ApiResponse
import com.task.trendy.domain.DataRepository
import com.task.trendy.domain.IDataRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: DashboardVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var dataRepo: IDataRepository

    @Before
    fun setUp() {
        dataRepo = mockk<DataRepository>()
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
            coEvery {
                dataRepo.getGithubProfiles(
                    query,
                    true
                )
            } returns response
            //2-Call
            viewModel = DashboardVM(dataRepo)
            viewModel.getTrendyGithubRepos(query, true)

            //3-verify
            Assert.assertEquals(false, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get trendy repo list with local data success`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Success<GitRepos>> {
                every { data } returns mockk {
                    every { repos } returns listOf(mockk(), mockk())
                }
            }
            coEvery {
                dataRepo.getGithubProfiles(
                    query,
                    false
                )
            } returns response
            //2-Call
            viewModel = DashboardVM(dataRepo)
            viewModel.getTrendyGithubRepos(query, false)

            //3-verify
            Assert.assertEquals(false, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get trendy repo list error`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getGithubProfiles(
                    query,
                    true
                )
            } returns response
            //2-Call
            viewModel = DashboardVM(dataRepo)
            viewModel.getTrendyGithubRepos(query, true)

            //3-verify
            Assert.assertEquals(true, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get trendy repo list with local data error`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getGithubProfiles(
                    query,
                    false
                )
            } returns response
            //2-Call
            viewModel = DashboardVM(dataRepo)
            viewModel.getTrendyGithubRepos(query, false)

            //3-verify
            Assert.assertEquals(true, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}