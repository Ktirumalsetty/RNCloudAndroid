package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.SchedulesListItemBinding
import com.rncloud.android.model.Schedule

class SchedulesAdapter(private val items:ArrayList<Schedule>): RecyclerView.Adapter<SchedulesAdapter.ScheduleVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleVH {
        return ScheduleVH(SchedulesListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ScheduleVH, position: Int) {
        holder.bind(items[position])
    }


    class ScheduleVH(binding: SchedulesListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val schedulesListItemBinding: SchedulesListItemBinding = binding

        fun bind(schedule: Schedule){
            schedulesListItemBinding.schedule = schedule
        }

    }
}
