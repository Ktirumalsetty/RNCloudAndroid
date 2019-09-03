package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rncloud.android.R
import com.rncloud.android.adapter.EducationsAdapter
import com.rncloud.android.adapter.EmploymentAdapter
import com.rncloud.android.databinding.EmploymentFragmentBinding
import com.rncloud.android.model.Education
import com.rncloud.android.model.Employment

class EmploymentFragment : BaseFragment<EmploymentFragmentBinding>() {

    override val layoutRes: Int
        get() = R.layout.employment_fragment

    companion object {
        fun newInstance() = EmploymentFragment()
    }

    private lateinit var viewModel: EmploymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmploymentViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getEmploymentInfo()
        viewModel.employmentRespLiveData.observe(this, Observer {

            if (it!=null && !it.hasError ){
                var employment = it.Result as ArrayList<Employment>
                binding.rvEmployments.adapter = EmploymentAdapter(employment)
            }else{
                showErrorRespMsg()
            }

        })
    }

}
