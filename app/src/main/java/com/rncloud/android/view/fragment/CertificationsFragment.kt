package com.rncloud.android.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rncloud.android.R
import com.rncloud.android.adapter.CertificationsAdapter
import com.rncloud.android.adapter.ClickListener
import com.rncloud.android.adapter.EmploymentAdapter
import com.rncloud.android.adapter.RecyclerTouchListener
import com.rncloud.android.databinding.CertificationsFragmentBinding
import com.rncloud.android.model.Certification
import com.rncloud.android.model.Employment
import com.rncloud.android.model.Licence
import com.rncloud.android.view.activity.AddEditCertificationActivity
import com.rncloud.android.view.activity.AddEditLicenceActivity

class CertificationsFragment : BaseFragment<CertificationsFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.certifications_fragment

    companion object {
        fun newInstance() = CertificationsFragment()
    }

    private lateinit var viewModel: CertificationsViewModel

    private lateinit var certifications: ArrayList<Certification>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CertificationsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getCertificationsInfo()

        viewModel.certificationsRespLiveData.observe(this, Observer {
            if (it!=null && !it.hasError ){
                certifications = it.Result as ArrayList<Certification>
                binding.rvCertifications.adapter = CertificationsAdapter(certifications)
            }else{
                showErrorRespMsg()
            }
        })

        binding.rvCertifications.addOnItemTouchListener(RecyclerTouchListener(context,binding.rvCertifications,object:
            ClickListener {
            override fun onClick(view: View, position: Int) {
                val certification = certifications[position]
                startActivity(Intent(context, AddEditCertificationActivity::class.java).putExtra("certificationObj",certification))
            }

            override fun onLongClick(view: View, position: Int) {
            }

        }))

    }

}
