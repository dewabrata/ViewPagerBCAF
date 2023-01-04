package com.bcaf.viewpagerbcaf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bcaf.viewpagerbcaf.adapter.PagerAdapter
import com.bcaf.viewpagerbcaf.fragment.Fragment1
import com.bcaf.viewpagerbcaf.fragment.Fragment2
import com.bcaf.viewpagerbcaf.fragment.Fragment3
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = arrayListOf<Fragment>()

        list.add(Fragment1.newInstance("",""))
        list.add(Fragment2.newInstance("",""))
        list.add(Fragment3.newInstance("",""))
        list.add(Fragment1.newInstance("",""))
        list.add(Fragment1.newInstance("",""))
        list.add(Fragment2.newInstance("",""))
        list.add(Fragment3.newInstance("",""))

        val myPagerAdapter = PagerAdapter(this,list)

        viewPager2.adapter = myPagerAdapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position ->

            tab.text = resources.getStringArray(R.array.TabMenuFragment)[position]

        }.attach()


    }
}