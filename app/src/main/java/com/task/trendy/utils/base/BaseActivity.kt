package com.task.trendy.utils.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.task.trendy.utils.base.interfaces.IBaseViewModel
import com.task.trendy.utils.extensions.observe
import com.task.trendy.utils.extensions.toast


abstract class BaseActivity<VB : ViewBinding, VM : IBaseViewModel> :
    AppCompatActivity(), View.OnClickListener {

    lateinit var mViewBinding: VB
    abstract fun getViewBinding(): VB
    abstract val viewModel: VM

    open fun onClick(id: Int) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)
        setObservers()
    }

    override fun onClick(view: View) {
        viewModel.onClick(view)
    }

    private fun setObservers() {
        observe(viewModel.clickEvent, ::handleClickEvent)
    }

    private fun handleClickEvent(clickEventId: Int) {
        onClick(clickEventId)
    }

    fun showToast(msg: String) {
        if (msg.isNotBlank()) {
            toast(msg)
        }
    }

    override fun onDestroy() {
        viewModel.clickEvent.removeObservers(this)
        super.onDestroy()
    }

}