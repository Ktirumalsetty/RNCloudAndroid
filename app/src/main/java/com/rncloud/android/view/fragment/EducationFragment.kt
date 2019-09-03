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
import com.rncloud.android.adapter.LicencesAdapter
import com.rncloud.android.databinding.EducationFragmentBinding
import com.rncloud.android.model.Education
import com.rncloud.android.model.Licence

class EducationFragment : BaseFragment<EducationFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.education_fragment

    companion object {
        fun newInstance() = EducationFragment()
    }

    private lateinit var viewModel: EducationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EducationViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getEducationsInfo()
        viewModel.educationRespLiveData.observe(this, Observer {

            if (it!=null && !it.hasError ){
                var educations = it.Result as ArrayList<Education>
                binding.rvEducations.adapter = EducationsAdapter(educations)
            }else{
                showErrorRespMsg()
            }
        })
    }

}
