package com.task.trendy.utils.base.sealed

sealed class UIEvent {
    data class Loading(val isLoading: Boolean) : UIEvent()
    data class Message(val message: String) : UIEvent()
    data class Error(val message: String) : UIEvent()
    data class Success(val data: Any) : UIEvent()
}