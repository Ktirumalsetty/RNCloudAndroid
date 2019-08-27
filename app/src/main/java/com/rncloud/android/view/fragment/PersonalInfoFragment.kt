package com.rncloud.android.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.bind

import com.rncloud.android.R
import com.rncloud.android.databinding.PersonalInfoFragmentBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.rncloud.android.view.activity.MainBottomNavigationDrawerActivity


class PersonalInfoFragment : BaseFragment<PersonalInfoFragmentBinding>() {

    override val layoutRes: Int
        get() = R.layout.personal_info_fragment //To change initializer of created properties use File | Settings | File Templates.

//    val binding:PersonalInfoFragmentBinding ?=null

    companion object {
        fun newInstance() = PersonalInfoFragment()
    }

    private lateinit var viewModel: PersonalInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG(),"onCreateView")
//        binding = bindView(inflater,com.rncloud.android.R.layout.personal_info_fragment, container, false)
       super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonalInfoViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG(),"onActivityCreated")
        viewModel.getPersonalInfo()
        viewModel.personalInfoResponseLiveData.observe(this, Observer {

            Log.d(TAG(),"personalInfoResponseLiveData "+it)
            if (it!=null) {
                if(!it.hasError){
//                    saveLoginRespToPrefs(resource.Result.xLoginController)
                    binding.personalInfo = it.Result[0]
                }else{
                    showAlrtMsg("response failed ...")
                }


            } else {
                showErrorRespMsg()
            }
        })
        // TODO: Use the ViewModel
    }

}
