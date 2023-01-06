package com.bcaf.viewpagerbcaf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bcaf.viewpagerbcaf.fragment.Fragment1
import com.bcaf.viewpagerbcaf.fragment.Fragment2
import com.bcaf.viewpagerbcaf.fragment.Fragment3
import com.bcaf.viewpagerbcaf.interfaces.CameraInterfaces
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_nav_bar.*
import kotlinx.android.synthetic.main.fragment_2.*
import lib.android.imagepicker.ImagePicker

class NavBarActivity : AppCompatActivity(), ImagePicker.OnImageSelectedListener,
    CameraInterfaces, NavigationView.OnNavigationItemSelectedListener {
    var toogle : ActionBarDrawerToggle? = null

    //penampung imagePicker
    lateinit var imagePicker: ImagePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)

        //inisiasi imagepicker
        imagePicker = ImagePicker(this, BuildConfig.APPLICATION_ID)
        //setListener Image Picker
        imagePicker.setImageSelectedListener(this)

        setupNavigationDrawer()
        showFirstFragment()

    }

    fun showFirstFragment(){
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()

        ft.add(R.id.container, Fragment1.newInstance("",""))
        ft.commit()
    }


    fun setupNavigationDrawer(){

        setSupportActionBar(toolbar)
        toogle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toogle!!)
        navigationMenu.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment : Fragment? = null

        when(item.itemId){
            R.id.itemWeather -> {
                fragment = Fragment1.newInstance("","")
            }
            R.id.itemMovie -> {
                fragment = Fragment2.newInstance("","")
            }
            R.id.item_about -> {
                fragment = Fragment3.newInstance("","")
            }
            R.id.item_setting -> {
               Toast.makeText(this,"About dipanggil",Toast.LENGTH_LONG).show()
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)

            if (fragment != null) {
                val ft =supportFragmentManager.beginTransaction()
                ft.replace(R.id.container,fragment)
                ft.commit()
            }

        return true

    }

    override fun onImageSelectFailure() {
        Toast.makeText(applicationContext,"Ambil gambar gagal",Toast.LENGTH_LONG).show()
    }

    override fun onImageSelectSuccess(imagePath: String) {
        Log.d("Photo File", imagePath)
        photo?.let {
            Glide.with(this@NavBarActivity)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load(imagePath)
                .into(it)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        imagePicker.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun openCamera() {
        imagePicker.takePhotoFromCamera()
    }

    override fun getImagePath(): String {
        TODO("Not yet implemented")
    }
}