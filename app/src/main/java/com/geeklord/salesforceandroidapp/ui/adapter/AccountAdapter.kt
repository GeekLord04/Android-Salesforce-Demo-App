package com.geeklord.salesforceandroidapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeklord.salesforceandroidapp.Models.Account
import com.geeklord.salesforceandroidapp.databinding.ItemAccountBinding

class AccountAdapter :
    ListAdapter<Account, AccountAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Account>() {
            override fun areItemsTheSame(old: Account, new: Account) = old.Id == new.Id
            override fun areContentsTheSame(old: Account, new: Account) = old == new
        }
    }

    inner class ViewHolder(val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val acc = getItem(position)
        holder.binding.tvAccountName.text = acc.Name
        holder.binding.tvAccountIndustry.text = acc.Industry ?: "—"
        holder.binding.tvAccountPhone.text = acc.Phone ?: "—"
    }
}