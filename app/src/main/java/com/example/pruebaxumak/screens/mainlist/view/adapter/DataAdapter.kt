package com.example.pruebaxumak.screens.mainlist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pruebaxumak.R
import com.example.pruebaxumak.databinding.ItemDataBinding
import com.example.pruebaxumak.screens.mainlist.model.MainResponse

class DataAdapter(private val listener : OnItemClickListener) : PagingDataAdapter<MainResponse, DataAdapter.MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item!=null){
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(data: MainResponse) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.img)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_background)
                    .into(image)
                name.text = data.name
                nickname.text=data.nickname
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(data: MainResponse)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MainResponse>() {
            override fun areItemsTheSame(oldItem: MainResponse, newItem: MainResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MainResponse, newItem: MainResponse): Boolean =
                oldItem == newItem
        }
    }

}