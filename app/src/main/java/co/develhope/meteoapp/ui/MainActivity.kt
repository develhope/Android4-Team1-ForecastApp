package co.develhope.meteoapp.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import co.develhope.meteoapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.home_background, null)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.cercaFragment -> {
                    if (!navController.popBackStack(R.id.cercaFragment, false)) {
                        it.onNavDestinationSelected(navController)
                    }

                    true
                }
                R.id.homeFragment -> {
                    if (!navController.popBackStack(R.id.homeFragment, false)) {
                        it.onNavDestinationSelected(navController)
                    }

                    true
                }
                else -> {
                    it.onNavDestinationSelected(navController)
                }
            }

        }

    }


}



