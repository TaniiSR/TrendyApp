package com.task.trendy.ui.dashaboard

import android.os.Bundle
import androidx.activity.viewModels
import com.task.trendy.databinding.ActivityDashboardBinding
import com.task.trendy.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding, IDashboardVM>() {
    override val viewModel: IDashboardVM by viewModels<DashboardVM>()
    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}