package com.task.trendy.utils.base.interfaces

import com.task.trendy.utils.base.SingleClickEvent

interface IBaseViewModel {
    val clickEvent: SingleClickEvent
    fun onClick(view: android.view.View)
}