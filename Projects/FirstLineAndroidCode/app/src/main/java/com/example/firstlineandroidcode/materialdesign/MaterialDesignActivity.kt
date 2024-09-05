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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.firstlineandroidcode.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MaterialDesignActivity : AppCompatActivity() {
    private val TAG = "MaterialDesign"

    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple_big),
        Fruit("Banana", R.drawable.banana_big),
        Fruit("Orange", R.drawable.orange_big),
        Fruit("Watermelon", R.drawable.watermelon_big),
        Fruit("Pear", R.drawable.pear_big),
        Fruit("Grape", R.drawable.grape_big),
        Fruit("Pineapple", R.drawable.pineapple_big),
        Fruit("Strawberry", R.drawable.strawberry_big),
        Fruit("Cherry", R.drawable.cherry_big),
        Fruit("Mango", R.drawable.mango_big)
    )
    val fruitList = ArrayList<Fruit>()

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

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = layoutManager
        /**
         * 给RecyclerView设置适配器
         * 在适配器中处理item相关的东西，包括item的布局，item点击事件注册等等
         * */
        val adapter = FruitAdapter2(this, fruitList)
        recyclerView.adapter = adapter

        val swipeRefresh : SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener(){
            thread {
                Thread.sleep(2000)
                runOnUiThread(){
                    Toast.makeText(this, "Update data success", Toast.LENGTH_SHORT).show()
                    swipeRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
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