package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.CertificationsListItemBinding
import com.rncloud.android.databinding.EmploymentListItemBinding
import com.rncloud.android.model.Certification

class CertificationsAdapter(private val items:ArrayList<Certification>): RecyclerView.Adapter<CertificationsAdapter.CertificationVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificationVH {
        return CertificationVH(
            CertificationsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CertificationVH, position: Int) {
        holder.bind(items[position])
    }


    class CertificationVH(binding: CertificationsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val employmentListItemBinding: CertificationsListItemBinding

        init {
            employmentListItemBinding = binding
        }

        fun bind(certification: Certification) {
            employmentListItemBinding.certification = certification
        }

    }
}