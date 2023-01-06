package com.bcaf.bcafrecycleview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.viewpagerbcaf.R
import com.bcaf.viewpagerbcaf.interfaces.BCAFListListener

import com.bcaf.viewpagerbcaf.data.model.User

class BCAFAdapter(val product:List<User>):RecyclerView.Adapter<BCAFViewHolder>() {
      lateinit var context: Context
      lateinit var listenerBCAF: BCAFListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BCAFViewHolder {

        context =parent.context
        return BCAFViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemuser,parent,false),listenerBCAF)
    }

    override fun onBindViewHolder(holder: BCAFViewHolder, position: Int) {
       holder.setData(context,product.get(position),position)
    }

    override fun getItemCount(): Int {
        return product.size
    }


    fun setListener(listen : BCAFListListener){
        listenerBCAF = listen
    }


}