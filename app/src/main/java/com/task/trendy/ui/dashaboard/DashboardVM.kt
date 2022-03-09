package com.task.trendy.ui.dashaboard

import com.task.trendy.domain.IDataRepository
import com.task.trendy.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    dataRepository: IDataRepository
) : BaseViewModel(), IDashboardVM
