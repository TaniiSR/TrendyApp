package com.task.trendy.ui.dashaboard.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.trendy.data.dtos.responsedtos.Profile
import com.task.trendy.databinding.LayoutItemRepoListViewBinding
import com.task.trendy.utils.base.interfaces.OnItemClickListener
import com.task.trendy.utils.extensions.gone
import com.task.trendy.utils.extensions.loadImage
import com.task.trendy.utils.extensions.visible

class RepoListItemViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(
        data: Profile,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemRepoListViewBinding -> {
                binding.ivProfile.loadImage(data.owner?.avatarUrl)
                binding.tvUserName.text = data.owner?.login
                binding.tvRepoName.text = data.name
                binding.tvLanguage.text = data.language
                binding.tvStarCount.text = data.stargazersCount.toString()
                binding.tvDescription.text = data.description
                binding.clMain.setOnClickListener {
                    data.showExpanded = if (data.showExpanded) {
                        binding.tvDescription.gone()
                        binding.llFooter.gone()
                        !data.showExpanded
                    } else {
                        binding.tvDescription.visible()
                        binding.llFooter.visible()
                        !data.showExpanded
                    }
                    onItemClickListener?.onItemClick(it, data, position)
                }
            }
        }
    }
}