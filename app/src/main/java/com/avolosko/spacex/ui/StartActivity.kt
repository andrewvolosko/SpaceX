package com.avolosko.spacex.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avolosko.spacex.R
import com.avolosko.spacex.ui.list.RocketsListFragment

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        showRocketsList()
    }

    private fun showRocketsList() {
        val fragment = RocketsListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        val tag = fragment.javaClass.simpleName

        transaction.replace(R.id.container, fragment, tag)
            .commit()
    }
}