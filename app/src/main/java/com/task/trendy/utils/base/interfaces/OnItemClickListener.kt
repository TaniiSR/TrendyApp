package com.task.trendy.utils.base.interfaces

import android.view.View

interface OnItemClickListener {
    fun onItemClick(view: View, data: Any, pos: Int)
}