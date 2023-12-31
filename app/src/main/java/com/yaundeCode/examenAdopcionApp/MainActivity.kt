package com.yaundeCode.examenAdopcionApp
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jakewharton.threetenabp.AndroidThreeTen
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.fragments.ProfileFragment
import com.yaundeCode.examenAdopcionApp.fragments.SettingsViewFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navView: NavigationView
    private var name: String? = null
    private lateinit var dogsViewModel: DogsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = intent.getStringExtra("name") ?: "Default Name"

        val bundle = bundleOf("username" to name!!)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navHostFragment.navController.setGraph(R.navigation.main_navgraph, bundle)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
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

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener (this)
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        // inicializo DAO
        val dogDao = AppDatabase.getDatabase(this).DogDao()
        // Observa la cantidad de perros favoritos
        dogDao.getFavoriteDogsCount().observe(this) { count ->
            // Actualiza el badge con la cantidad de perros favoritos
            val badgeFavorite = bottomNavView.getOrCreateBadge(R.id.favorite)
            badgeFavorite.backgroundColor = ContextCompat.getColor(this, R.color.badge_favorite)
            badgeFavorite.isVisible = count > 0
            badgeFavorite.number = count
        }
        dogDao.getDogsByOwnerCount(name!!).observe(this){ count ->
            val badgeAdoption = bottomNavView.getOrCreateBadge(R.id.adoption)
            badgeAdoption.backgroundColor = ContextCompat.getColor(this, R.color.error)
            badgeAdoption.isVisible = count > 0
            badgeAdoption.number = count
        }
        
        bottomNavView.setOnItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.dogsListFragment -> {
                    val bundle = bundleOf("username" to name!!)
                    navHostFragment.navController.navigate(R.id.dogsListFragment, bundle)
                    true
                }
                R.id.favorite -> {
                    val bundle = bundleOf("username" to name!!)
                    navHostFragment.navController.navigate(R.id.favorite, bundle)
                    true
                }
                R.id.adoption -> {
                    val bundle = bundleOf("name" to name!!)
                    navHostFragment.navController.navigate(R.id.adoption, bundle)
                    true
                }
                R.id.formFragment -> {
                    val bundle = bundleOf("ownerName" to name!!)
                    navHostFragment.navController.navigate(R.id.formFragment, bundle)
                    true
                }
                else -> false
            }
        }

        // Escuchar a la navegación
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingsViewFragment, R.id.profileFragment -> {
                    supportActionBar?.hide()
                }
                else -> {
                    supportActionBar?.show()
                }
            }
        }

        AndroidThreeTen.init(this)
        val headerView: View = navView.getHeaderView(0)
        val usernameTextView: TextView = headerView.findViewById(R.id.nav_header_username)
        usernameTextView.text = name
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_profile -> {
                val bundle = bundleOf("name" to name!!)
                navHostFragment.navController.navigate(R.id.profileFragment, bundle)
            }
            R.id.nav_item_setting -> {
                navHostFragment.navController.navigate(R.id.settingsViewFragment)
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
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }

        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun updateDrawerImage(imageUri: Uri) {
        val headerView: View = navView.getHeaderView(0)
        val imageView: ImageView = headerView.findViewById(R.id.nav_header_imageUser)
        Glide.with(this)
            .load(imageUri)
            .into(imageView)
    }

}
