package com.example.firstlineandroidcode.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.firstlineandroidcode.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MaterialDesignActivity : AppCompatActivity() {
    private val TAG = "MaterialDesign"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navView : NavigationView = findViewById(R.id.navView)
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
           when(it.itemId){
               R.id.navCall -> Log.d(TAG, "on click: call")
               R.id.navFriends -> Log.d(TAG, "on click: friends")
               R.id.navLocation -> Log.d(TAG, "on click: location")
               R.id.navMail -> Log.d(TAG, "on click: mail")
               R.id.navTask -> Log.d(TAG, "on click: task")
           }
            val  drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
            drawerLayout.closeDrawers()
            true
        }

        val fab : FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener(){
//            finish()
            /**
             * 设置多个action不会报错
             * 但是只有最后一个生效，前面的被覆盖
             * */
            Snackbar.make(it, "Snack bar Test", Snackbar.LENGTH_SHORT).setAction("Action"){
                Toast.makeText(this, "do action", Toast.LENGTH_SHORT).show()
            }.setAction("Action 2"){
                Toast.makeText(this, "do action 2", Toast.LENGTH_SHORT).show()
            }.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
                drawerLayout.openDrawer(GravityCompat.START)
            }
            R.id.backup -> {
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}