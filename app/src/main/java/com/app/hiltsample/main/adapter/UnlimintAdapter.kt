package com.app.hiltsample.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.hiltsample.data.model.JokesModel
import com.app.hiltsample.databinding.ItemLayoutBinding

class UnlimintAdapter() : RecyclerView.Adapter<UnlimintAdapter.DataViewHolder>() {
    private val users = ArrayList<JokesModel>()

    class DataViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: JokesModel) {
            binding.textJoke.text = user.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }


    fun updateData(list: JokesModel) {
        if (users.size == 10) {
            users.removeAt(0)
            notifyItemRemoved(0)
            users.add(list)
            notifyItemInserted(users.size - 1)
        } else {
            users.add(list)
            notifyItemInserted(users.size)
        }
    }


}