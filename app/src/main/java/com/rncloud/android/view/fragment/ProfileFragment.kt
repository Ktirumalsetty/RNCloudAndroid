package com.rncloud.android.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

import com.rncloud.android.R
import com.rncloud.android.databinding.JobsFragmentBinding
import com.rncloud.android.databinding.ProfileFragmentBinding
import com.rncloud.android.view.activity.MainBottomNavigationDrawerActivity

class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: ProfileFragmentBinding

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG(),"onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewpager.adapter =ProfileFragmentPagerAdapter((activity as MainBottomNavigationDrawerActivity).supportFragmentManager)
        binding.viewpager.offscreenPageLimit = 6
        binding.tablayout.setupWithViewPager(binding.viewpager)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG(),"onActivityCreated")
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)


    }



    class ProfileFragmentPagerAdapter constructor(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){


        override fun getItem(position: Int): Fragment {
            var fragment: Fragment = Fragment()
            when (position) {
                0 -> fragment = PersonalInfoFragment.newInstance()
                1 -> fragment = ScheduleFragment.newInstance()
                2 -> fragment = ScheduleFragment.newInstance()
                3 -> fragment = ScheduleFragment.newInstance()
                4 -> fragment = ScheduleFragment.newInstance()
                5 -> fragment = ScheduleFragment.newInstance()
            }

            return fragment

        }

        override fun getCount(): Int {
            return 6
        }

        override fun getPageTitle(position: Int): CharSequence? {
            var title:String =""
            when (position) {
                0 -> title ="Personal Info"
                1 -> title ="Licences"
                2 -> title ="Certifications"
                3 -> title ="Education"
                4 -> title ="Employement"
                5 -> title ="Medical Documents"
            }
        return title
        }

//        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            super.destroyItem(container, position, `object`)
//        }

    }
}
