package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration

import com.rncloud.android.R
import com.rncloud.android.adapter.LicencesAdapter
import com.rncloud.android.adapter.SchedulesAdapter
import com.rncloud.android.databinding.ScheduleFragmentBinding
import com.rncloud.android.model.Licence
import com.rncloud.android.model.Schedule

class ScheduleFragment : BaseFragment<ScheduleFragmentBinding>() {
    override val layoutRes: Int
        get() = R.layout.schedule_fragment //To change initializer of created properties use File | Settings | File Templates.

    private lateinit var schedules: ArrayList<Schedule>

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG(),"onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        binding.recyclerview.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel.getSchedules()

        viewModel.scheduleRespLiveData.observe(this, Observer {
            if (it!=null && !it.hasError ){
                schedules = it.Result as ArrayList<Schedule>
                binding.recyclerview.adapter = SchedulesAdapter(schedules)
            }else{
                showErrorRespMsg()
            }
        })

    }

}
