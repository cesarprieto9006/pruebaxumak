package com.example.pruebaxumak.screens.mainlist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.pruebaxumak.R
import com.example.pruebaxumak.databinding.ItemDataBinding
import com.example.pruebaxumak.screens.mainlist.model.DataResponse

class DataAdapter(private val listener: DataItemListener) : RecyclerView.Adapter<DataViewHolder>() {

    interface DataItemListener {
        fun onClickedFavorite(dataResponse: DataResponse)
    }

    private val items = ArrayList<DataResponse>()

    fun setItems(items: ArrayList<DataResponse>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding: ItemDataBinding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(items[position])
}

class DataViewHolder(
    private val itemBinding: ItemDataBinding,
    private val listener: DataAdapter.DataItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var data: DataResponse

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: DataResponse) {
        this.data = item
        itemBinding.name.text = item.name
        itemBinding.nickname.text = item.nickname
        Glide.with(itemBinding.root)
            .load(item.img)
            .transform(CircleCrop())
            .into(itemBinding.image)
        itemBinding.favorites.setOnClickListener {
            listener.onClickedFavorite(data)
        }
        when {
            !data.State -> {
                itemBinding.favorites.setImageResource(R.drawable.ic_heart_clk)
            }
            else -> {
                itemBinding.favorites.setImageResource(R.drawable.ic_heart)
            }
        }
    }

    override fun onClick(v: View?) {
    }
}
