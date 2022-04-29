package com.example.testappattractor.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.testappattractor.R

abstract class BaseAdapter<T, Binding : ViewBinding>(
    private val holderLayoutId: Int,
    var data: List<T>,
    private val inflater: (LayoutInflater) -> Binding
) : RecyclerView.Adapter<BaseAdapter<T, Binding>.BaseViewHolder>() {

    var listener: IBaseAdapterClickListener<T>? = null
    private var _binding: Binding? = null
    val binding get() = _binding!!
    var lastPosition: Int = -1
    var position: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        _binding = inflater.invoke(LayoutInflater.from(parent.context))
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(data[position])
        setAnimation(holder.itemView, position)
        this.position = position
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    abstract fun onBind(binding: Binding, model: T)

    private fun setAnimation(viewToAnimation: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimation.context, R.anim.item_anim)
            viewToAnimation.startAnimation(animation)
            lastPosition = position
        }
    }

    inner class BaseViewHolder(binding: Binding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: T) {
            onBind(binding, model)
            itemView.setOnClickListener { listener?.onClick(model) }
        }
    }

    interface IBaseAdapterClickListener<T> {
        fun onClick(model: T)
    }
}