package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.JobsListItemBinding
import com.rncloud.android.model.Job

class JobsAdapter(private val items:ArrayList<Job>): RecyclerView.Adapter<JobsAdapter.JobVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobVH {
        return JobVH(JobsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: JobVH, position: Int) {
        holder.bind(items[position])
    }


    class JobVH(binding: JobsListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val schedulesListItemBinding: JobsListItemBinding = binding

        fun bind(schedule: Job){
            schedulesListItemBinding.job = schedule
        }

    }
}
