package com.bcaf.viewpagerbcaf.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcaf.viewpagerbcaf.MainActivity
import com.bcaf.viewpagerbcaf.interfaces.CameraInterfaces
import com.bcaf.viewpagerbcaf.R
import com.bcaf.viewpagerbcaf.data.model.User
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_2.view.*
import java.io.File
import java.util.UUID


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var dialog :LottieProgressDialog
    lateinit var camera : CameraInterfaces

    lateinit var firebaseStorage : FirebaseStorage
    lateinit var storeRef : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        camera = (context as CameraInterfaces)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_2, container, false)

        firebaseStorage = FirebaseStorage.getInstance()
        storeRef = firebaseStorage.reference


        dialog= LottieProgressDialog(
            context = requireContext(),
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_1,
            title = null,
            titleVisible = null
        )



        view.btnAddData.setOnClickListener(View.OnClickListener {


           upload_data()



        })

        view.btnCaptureCamera.setOnClickListener(View.OnClickListener {


            camera.openCamera()

        })


        return view
    }


    fun save_data(data:User){
       dialog.show()
        val db = Firebase.firestore

        val user = hashMapOf(
            "nama" to data.name,
            "alamat" to data.alamat,
            "telepon" to data.telepon,
            "gambar" to data.gambar

        )
        db.collection("User")
            .add(user)
            .addOnSuccessListener {
                documentRef ->
                Log.d("Succes add data" , documentRef.toString())
                clearText()
                dialog.hide()
            }
            .addOnFailureListener {
                e->
                Log.e("Error add data", e.toString())
                dialog.hide()
            }
    }

    fun clearText(){
        txtAddAlamat.setText("")
        txtAddNama.setText("")
        txtAddTelp.setText("")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun upload_data(){

        var ref = storeRef.child("gambar/"+UUID.randomUUID().toString())
        if(camera.getImagePath()!=null){

            var uploadData = ref.putFile(Uri.fromFile(File(camera.getImagePath()))).addOnSuccessListener {

                    task ->

                ref.downloadUrl.addOnSuccessListener {
                        it ->
                    Log.d("URi",it.toString())
                    save_data(User("",txtAddNama.text.toString(),
                        txtAddAlamat.text.toString(),txtAddTelp.text.toString(),it.toString()))
                }
            }

        }


    }



}