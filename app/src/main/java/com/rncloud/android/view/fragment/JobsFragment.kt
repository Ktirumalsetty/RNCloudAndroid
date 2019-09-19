package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.rncloud.android.R
import com.rncloud.android.adapter.JobsAdapter
import com.rncloud.android.adapter.SchedulesAdapter
import com.rncloud.android.databinding.JobsFragmentBinding
import com.rncloud.android.model.Job
import com.rncloud.android.model.Schedule

class JobsFragment : BaseFragment<JobsFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.jobs_fragment

    companion object {
        fun newInstance() = JobsFragment()
    }

    private lateinit var viewModel: JobsViewModel
    private lateinit var jobs: ArrayList<Job>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(JobsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getJobs()
        viewModel.jobRespLiveData.observe(this, Observer {
            if (it!=null && !it.hasError ){
                jobs = it.Result as ArrayList<Job>
                binding.recyclerview.adapter = JobsAdapter(jobs)
            }else{
                showErrorRespMsg()
            }
        })
    }

}
