package com.rncloud.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.rncloud.android.R
import com.rncloud.android.model.LicenceState

class LicenceStatesSpinnerAdapter(private val context:Context,var items: ArrayList<LicenceState>): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: LicenceStateVH
        if (convertView == null) {
            view = mInflater.inflate(R.layout.licence_state_spinner_item, parent, false)
            vh = LicenceStateVH(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as LicenceStateVH
        }

        // setting adapter item height programatically.

//        val params = view.layoutParams
//        params.height = 60
//        view.layoutParams = params

        vh.label.text = (items[position] as LicenceState).LongValue


        return view
    }

    override fun getItem(position: Int): Any {
            return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    private class LicenceStateVH(row: View?) {

        val label: TextView = row?.findViewById(R.id.tv_state_name) as TextView

    }
}