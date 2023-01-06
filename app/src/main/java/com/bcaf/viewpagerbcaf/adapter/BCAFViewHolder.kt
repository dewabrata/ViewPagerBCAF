package com.bcaf.bcafrecycleview.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.viewpagerbcaf.interfaces.BCAFListListener
import com.bcaf.viewpagerbcaf.data.model.User
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.itemuser.view.*


//view itemuser.xml
class BCAFViewHolder(view: View,parent: BCAFListListener):RecyclerView.ViewHolder(view) {

    val view = view
    val parent = parent



    fun setData(context : Context, data: User, position:Int){
            view.txtNamaUser.setText(data.name)
            view.txtAlamatUser.setText(data.alamat)
            view.txtTeleponUser.setText(data.telepon)



            Glide.with(context)
            .load(data.gambar)
            .into(view.gambar)

            view.btnDelete.setOnClickListener(View.OnClickListener {
            val db = Firebase.firestore

            db.collection("User").document(data.id).delete().addOnCompleteListener {
                task ->

                parent.onRefresh()
                Toast.makeText(context ,"Data ${data.name} berhasil di delete",Toast.LENGTH_LONG).show()
            }
                .addOnFailureListener {
                it ->
                    Log.e("Error",it.message.toString())
                }

        })


    }


}