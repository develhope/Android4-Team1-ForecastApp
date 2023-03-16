package co.develhope.meteoapp.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.onNavDestinationSelected
import co.develhope.meteoapp.R
import co.develhope.meteoapp.ui.adapter.SplashActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

       /* Handler().postDelayed({
            val intent = Intent(this@MainActivity,SplashActivity ::class.java)
            startActivity(intent)
        },3000)*/

        window.statusBarColor = resources.getColor(R.color.home_background, null)



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
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



