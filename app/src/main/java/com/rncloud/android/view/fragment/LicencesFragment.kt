package com.rncloud.android.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.rncloud.android.R
import com.rncloud.android.adapter.ClickListener
import com.rncloud.android.adapter.LicencesAdapter
import com.rncloud.android.adapter.RecyclerTouchListener
import com.rncloud.android.databinding.LicencesFragmentBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.view.activity.AddEditLicenceActivity

class LicencesFragment : BaseFragment<LicencesFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.licences_fragment


    companion object {
        fun newInstance() = LicencesFragment()
    }


    private lateinit var viewModel: LicencesViewModel

    private lateinit var licences: ArrayList<Licence>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LicencesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getLicences()
        viewModel.licenceRespLiveData.observe(this, Observer {

            if (it!=null && !it.hasError ){
                licences = it.Result as ArrayList<Licence>
                binding.rvLicences.adapter = LicencesAdapter(licences)
            }else{
                showErrorRespMsg()
            }

        })

        binding.rvLicences.addOnItemTouchListener(RecyclerTouchListener(context,binding.rvLicences,object:ClickListener{
            override fun onClick(view: View, position: Int) {
                val licence = licences[position]
                startActivity(Intent(context,AddEditLicenceActivity::class.java).putExtra("licenceObj",licence))
            }

            override fun onLongClick(view: View, position: Int) {
            }

        }))

    }

}
