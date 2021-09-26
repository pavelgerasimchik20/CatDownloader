package com.geras.cats.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.geras.cats.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openCatFragment()

    }

    private fun openCatFragment() {
        val fragment = CatFragment.newInstance()
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
        transaction.commit()
    }
}