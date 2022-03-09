package com.task.trendy.ui.dashaboard

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.task.trendy.R
import com.task.trendy.data.dtos.responsedtos.Profile
import com.task.trendy.databinding.ActivityDashboardBinding
import com.task.trendy.utils.base.BaseActivity
import com.task.trendy.utils.base.interfaces.OnItemClickListener
import com.task.trendy.utils.base.sealed.UIEvent
import com.task.trendy.utils.extensions.gone
import com.task.trendy.utils.extensions.observe
import com.task.trendy.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding, IDashboardVM>() {
    override val viewModel: IDashboardVM by viewModels<DashboardVM>()
    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
        setListener()
        setUpRecyclerView()
        viewModel.getTrendyGithubRepos(query = viewModel.query, false)
    }

    private fun setUpRecyclerView() {
        viewModel.adaptor.onItemClickListener = rvItemClickListener
        mViewBinding.recyclerView.adapter = viewModel.adaptor
    }

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is Profile -> {
                    // handle later
                }
            }
        }
    }

    private fun handleRepoList(list: List<Profile>) {
        if (!list.isNullOrEmpty()) {
            viewModel.adaptor.setList(list)
        } else setErrorView()
    }

    private fun setListener() {
        mViewBinding.btnRetry.setOnClickListener(this)
        mViewBinding.swRefresh.setOnRefreshListener {
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.fetchFreshData()
        }
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnRetry -> viewModel.fetchFreshData()
        }
    }

    private fun handleUiState(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.Loading -> setLoadingView()
            is UIEvent.Success -> setSuccessView()
            is UIEvent.Error -> setErrorView()
            is UIEvent.Message -> showToast(uiEvent.message)
        }
    }

    private fun setErrorView() {
        mViewBinding.errorView.visible()
        setShimmerLoading(false)
        mViewBinding.recyclerView.gone()
    }

    private fun setSuccessView() {
        mViewBinding.recyclerView.visible()
        setShimmerLoading(false)
        mViewBinding.errorView.gone()
    }

    private fun setLoadingView() {
        mViewBinding.errorView.gone()
        mViewBinding.recyclerView.gone()
        setShimmerLoading(true)
    }

    private fun setShimmerLoading(isShow: Boolean) {
        if (isShow) {
            mViewBinding.shimmerLayout.shimmerFrameLayout.visible()
            mViewBinding.shimmerLayout.shimmerFrameLayout.startShimmer()
        } else {
            mViewBinding.shimmerLayout.shimmerFrameLayout.gone()
            mViewBinding.shimmerLayout.shimmerFrameLayout.stopShimmer()
        }
    }

    private fun viewModelObservers() {
        observe(viewModel.repoLists, ::handleRepoList)
        observe(viewModel.uiState, ::handleUiState)
    }
}
