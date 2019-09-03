package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.EmploymentListItemBinding
import com.rncloud.android.model.Employment

class EmploymentAdapter(private val items:ArrayList<Employment>): RecyclerView.Adapter<EmploymentAdapter.EmployerVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployerVH {
        return EmployerVH(EmploymentListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EmployerVH, position: Int) {
        holder.bind(items[position])
    }


    class EmployerVH(binding: EmploymentListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val employmentListItemBinding: EmploymentListItemBinding
        init {
            employmentListItemBinding = binding
        }

        fun bind(employment: Employment){
            employmentListItemBinding.employment = employment
        }

    }
}