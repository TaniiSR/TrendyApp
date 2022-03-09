package com.task.trendy.ui.dashaboard

import androidx.lifecycle.LiveData
import com.task.trendy.data.dtos.responsedtos.Profile
import com.task.trendy.ui.dashaboard.adapter.RepoListAdapter
import com.task.trendy.utils.base.interfaces.IBaseViewModel
import com.task.trendy.utils.base.sealed.UIEvent

interface IDashboardVM : IBaseViewModel {
    val uiState: LiveData<UIEvent>
    val repoLists: LiveData<List<Profile>>
    val query: String
    val adaptor: RepoListAdapter
    fun fetchFreshData()
    fun getTrendyGithubRepos(query: String, isRefresh: Boolean)
}