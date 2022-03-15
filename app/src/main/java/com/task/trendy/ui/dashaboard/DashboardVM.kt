package com.task.trendy.ui.dashaboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.trendy.data.dtos.responsedtos.Profile
import com.task.trendy.data.remote.baseclient.ApiResponse
import com.task.trendy.domain.IDataRepository
import com.task.trendy.ui.dashaboard.adapter.RepoListAdapter
import com.task.trendy.utils.base.BaseViewModel
import com.task.trendy.utils.base.sealed.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val dataRepository: IDataRepository
) : BaseViewModel(), IDashboardVM {
    private val _uiState: MutableLiveData<UIEvent> = MutableLiveData()
    override val uiState: LiveData<UIEvent> = _uiState

    private val _repoLists: MutableLiveData<List<Profile>> = MutableLiveData()
    override val repoLists: LiveData<List<Profile>> = _repoLists

    override val query: String = "language=+sort:stars"
    override val adaptor: RepoListAdapter = RepoListAdapter(mutableListOf())

    override fun fetchFreshData() {
        getTrendyGithubRepos(query = query, true)
    }

    override fun getTrendyGithubRepos(query: String, isRefresh: Boolean) {
        launch {
            _uiState.postValue(UIEvent.Loading)
            val response = dataRepository.getGithubProfiles(query, isRefresh)
            withContext(Dispatchers.Main) {
                when (response) {
                    is ApiResponse.Success -> {
                        _repoLists.value = response.data.repos ?: listOf()
                        _uiState.value = UIEvent.Success
                    }
                    is ApiResponse.Error -> {
                        _repoLists.value = listOf()
                        _uiState.value = UIEvent.Error(response.error.message)
                    }
                }
            }
        }
    }
}
