package com.bcaf.bcafrecycleview.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.viewpagerbcaf.data.model.User
import kotlinx.android.synthetic.main.itemuser.view.*


//view itemuser.xml
class BCAFViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val view = view


    fun setData(context : Context, data: User, position:Int){



            view.txtNamaUser.setText(data.name)
            view.txtAlamatUser.setText(data.alamat)
            view.txtTeleponUser.setText(data.telepon)
         //   Glide.with(context).load(data.gambar).into(view.gambar);


    }
}