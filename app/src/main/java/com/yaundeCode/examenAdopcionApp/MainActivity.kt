package com.yaundeCode.examenAdopcionApp

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jakewharton.threetenabp.AndroidThreeTen
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter
import com.yaundeCode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.fragments.SettingsViewFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    private lateinit var dogDao: DogDao


    override fun onCreate(savedInstanceState: Bundle?) {
        val nombre = intent.getStringExtra("nombre") ?: "Default Name"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle =
                ActionBarDrawerToggle(
                        this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close
                )

        toggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        //            // Cargamos el fragmento DogsListFragment en el contenedor
        //            val fragment = DogsListFragment()
        //            supportFragmentManager.beginTransaction()
        //                .replace(R.id.nav_host, fragment)
        //                .commit()

        dogDao = AppDatabase.getDatabase(this).DogDao()


        AndroidThreeTen.init(this)
        val headerView: View = navigationView.getHeaderView(0)
        val usernameTextView: TextView = headerView.findViewById(R.id.nav_header_username)
        usernameTextView.text = nombre


        // Obtén una referencia al elemento de menú de favoritos
        val favoritesMenuItem = bottomNavView.menu.findItem(R.id.favorite)

        // Obtén la cantidad de perros favoritos
        val ownerName = "Martin"
        val dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.loadDogs()

        var favoriteCount: Int = 0

        dogsViewModel.dogList.observe(this) { dogs ->
            favoriteCount = (dogs.filter { dog -> dog.favorite }).count()

            bottomNavView.removeBadge(favoritesMenuItem.itemId)

            if(favoriteCount > 0) {
                // Establece el recuento de la insignia en el elemento de menú de favoritos
                bottomNavView.getOrCreateBadge(favoritesMenuItem.itemId).number = favoriteCount
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_profile -> Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
            R.id.nav_item_setting -> {
                val settingsViewFragment = SettingsViewFragment.newInstance("", "")
                supportFragmentManager
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.drawer_layout, settingsViewFragment)
                        .addToBackStack("open_config")
                        .commit()
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        // Actualizar la insignia en el icono de favoritos
        //updateFavoritesBadge()
    }

}
