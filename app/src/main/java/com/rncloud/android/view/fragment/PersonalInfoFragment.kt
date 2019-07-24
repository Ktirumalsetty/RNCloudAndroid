package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rncloud.android.R

class PersonalInfoFragment : BaseFragment() {

    companion object {
        fun newInstance() = PersonalInfoFragment()
    }

    private lateinit var viewModel: PersonalInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG(),"onCreateView")
        return inflater.inflate(R.layout.personal_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG(),"onActivityCreated")
        viewModel = ViewModelProviders.of(this).get(PersonalInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
