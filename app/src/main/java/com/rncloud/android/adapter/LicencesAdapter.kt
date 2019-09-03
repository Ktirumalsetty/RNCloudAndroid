package com.rncloud.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rncloud.android.databinding.LicenceRowItemBinding
import com.rncloud.android.model.Licence

class LicencesAdapter(private val items:ArrayList<Licence>): RecyclerView.Adapter<LicencesAdapter.LicenceVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenceVH {
        return LicenceVH(LicenceRowItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
            return items.size
    }

    override fun onBindViewHolder(holder: LicenceVH, position: Int) {
        holder.bind(items[position])
    }


    class LicenceVH(binding:LicenceRowItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val licenceRowItemBinding:LicenceRowItemBinding
        init {
            licenceRowItemBinding = binding
        }

        fun bind(licence:Licence){
            licenceRowItemBinding.licence = licence
        }

    }
}