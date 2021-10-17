package com.waseefakhtar.weatherapp.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T, VB : ViewBinding> : RecyclerView.Adapter<BaseRecyclerViewAdapter.Companion.BaseViewHolder<VB>>() {


    abstract fun setBinding(parent: ViewGroup): VB
    abstract fun onBindData(holder: BaseViewHolder<VB>, item: T, position: Int)
    abstract fun setItemsTheSame(oldItem: T, newItem: T): Boolean
    abstract fun serContentsTheSame(oldItem: T, newItem: T): Boolean


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(setBinding(parent))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        onBindData(holder, differ.currentList[position], position)
    }

    override fun getItemCount(): Int = differ.currentList.size

    protected val differCallBack = object : DiffUtil.ItemCallback<T>(){
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return serContentsTheSame(oldItem, newItem)
        }

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return setItemsTheSame(oldItem, newItem)
        }
    }

    abstract var differ: AsyncListDiffer<T>


    companion object {
        class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)
    }

    var onItemClick : ((item: T) -> Unit)? = null

}