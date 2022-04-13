package com.globits.nimpe.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.globits.nimpe.R
import com.globits.nimpe.data.model.Category

class CategoryAdapter(val context: Context,val list: List<Category>) :BaseAdapter(){
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view=LayoutInflater.from(context).inflate(R.layout.category_list_item,parent,false)
        view.findViewById<TextView>(R.id.lable_category).text=list[position].title
        return view
    }

}