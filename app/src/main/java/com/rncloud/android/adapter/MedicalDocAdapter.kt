package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.MedicalDocsListItemBinding
import com.rncloud.android.model.MedicalDoc

class MedicalDocAdapter(private val items:ArrayList<MedicalDoc>): RecyclerView.Adapter<MedicalDocAdapter.MedicalDocVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalDocVH {
        return MedicalDocVH(MedicalDocsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MedicalDocVH, position: Int) {
        holder.bind(items[position])
    }


    class MedicalDocVH(binding: MedicalDocsListItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val medicalDocsListItemBinding: MedicalDocsListItemBinding = binding

        fun bind(medicalDoc: MedicalDoc){
            medicalDocsListItemBinding.medicalDoc = medicalDoc
        }

    }
}