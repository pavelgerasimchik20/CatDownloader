package com.geras.cats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geras.cats.R
import com.geras.cats.data.Cat

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openCatFragment()
    }

    private fun openCatFragment() {
        val fragment = CatFragment.newInstance()
        commitTransaction(fragment)
    }

    fun openBigCatFragment(cat: Cat) {
        val fragment = BigCatFragment.newInstance(cat)
        commitTransaction(fragment)
    }

    private fun commitTransaction(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }
}