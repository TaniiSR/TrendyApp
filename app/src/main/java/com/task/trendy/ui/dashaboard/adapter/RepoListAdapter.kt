package com.task.trendy.ui.dashaboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.task.trendy.R
import com.task.trendy.data.dtos.responsedtos.Profile
import com.task.trendy.databinding.LayoutItemRepoListViewBinding
import com.task.trendy.utils.base.BaseRecyclerAdapter

class RepoListAdapter(
    private val list: MutableList<Profile>,
) : BaseRecyclerAdapter<Profile, RepoListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): RepoListItemViewHolder {
        return RepoListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_item_repo_list_view
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return LayoutItemRepoListViewBinding.inflate(layoutInflater, viewGroup, false)
    }
}