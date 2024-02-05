package com.mlbench.fitmeapp.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.mlbench.fitmeapp.R
import com.mlbench.fitmeapp.base.BaseActivity
import com.mlbench.fitmeapp.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    lateinit var binding:ActivityAuthBinding
    private lateinit var controller: NavController
    private var destinationId: Int? = null
    private val listener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            destinationId = destination.id

        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = Navigation.findNavController(this, R.id.authHostFragment)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (destinationId == null || destinationId == R.id.authSignupFragment || destinationId == R.id.authLoginFragment) {
                    finish()
                } else {
                    /*if (destinationId == R.id.authIntroFragment){
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .build()
                        GoogleSignIn.getClient(this@AuthActivity, gso).signOut()
                    }*/
                    Navigation.findNavController(this@AuthActivity, R.id.authHostFragment)
                        .popBackStack(destinationId!!, true)

                }
            }

        })


    }

    override fun attachViewMode() {

    }
}