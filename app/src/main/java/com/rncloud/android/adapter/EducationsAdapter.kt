package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.EducationListItemBinding
import com.rncloud.android.databinding.LicenceRowItemBinding
import com.rncloud.android.model.Education
import com.rncloud.android.model.Licence

class EducationsAdapter(private val items:ArrayList<Education>): RecyclerView.Adapter<EducationsAdapter.EducationVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationVH {
        return EducationVH(EducationListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EducationVH, position: Int) {
        holder.bind(items[position])
    }


    class EducationVH(binding: EducationListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val licenceRowItemBinding: EducationListItemBinding
        init {
            licenceRowItemBinding = binding
        }

        fun bind(education: Education){
            licenceRowItemBinding.education = education
        }

    }
}