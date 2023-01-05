package com.bcaf.viewpagerbcaf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registrasi_user.*

class RegistrasiUser : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi_user)

        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()

        btnRegistrasi.setOnClickListener(View.OnClickListener {

            if(!txtRegEmail.text.toString().equals("")  && !txtRegPass.text.toString().equals("")
                && !txtRegRePass.text.toString().equals("")){

                if(txtRegPass.text.toString().equals(txtRegRePass.text.toString())){
                    createUser()
                }else{
                    Toast.makeText(this,"Maaf password yang dimasukan berbeda",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Isi semua field",Toast.LENGTH_LONG).show()
            }

        })

    }
    fun createUser(){

        firebaseAuth.createUserWithEmailAndPassword(txtRegEmail.text.toString(),txtRegPass.text.toString()).addOnCompleteListener{
            task ->

            if(task.isSuccessful){
                Toast.makeText(this,"User berhasil dibuat",Toast.LENGTH_LONG).show()
            }
                finish()

            }

        }

}