package com.geras.cats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geras.cats.R
import com.geras.cats.data.Cat

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

    public fun openBigCatFragment(cat: Cat) {
        val fragment = BigCatFragment.newInstance(cat)
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
        transaction.commit()
    }
}