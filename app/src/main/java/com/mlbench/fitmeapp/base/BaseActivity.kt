package com.mlbench.fitmeapp.base

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import com.mlbench.fitmeapp.R
import com.mlbench.fitmeapp.Utils.SharePref
import com.mlbench.fitmeapp.Utils.deleteToken
import com.mlbench.fitmeapp.view.Activities.AuthActivity

abstract class BaseActivity : AppCompatActivity() {

    var progressBar: KProgressHUD? = null
    var bottomSheetDialog: Dialog? = null

    private lateinit var options: NavOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(true)
            .setBackgroundColor(Color.TRANSPARENT)
            .setAnimationSpeed(1)
            .setDimAmount(0.5f)


        options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()


       /* bottomSheetDialog = Dialog(this, R.style.Custom_Dialog_Full)
        bottomSheetDialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        bottomSheetDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        bottomSheetDialog?.setCancelable(true)
        bottomSheetDialog?.setCanceledOnTouchOutside(false)
        bottomSheetDialog?.setContentView(R.layout.dialog_loader)*/


    }


    fun showLoadingBar() {
        if (progressBar != null && !progressBar!!.isShowing)
            progressBar?.show()

//        if (bottomSheetDialog != null && !bottomSheetDialog!!.isShowing)
//            bottomSheetDialog?.show()
    }

    fun hideLoadingBar() {
        if (progressBar != null && progressBar!!.isShowing)
            progressBar?.dismiss()

//        if (bottomSheetDialog != null && bottomSheetDialog!!.isShowing)
//            bottomSheetDialog?.dismiss()
    }

    abstract fun attachViewMode()


    fun replaceFragmentInMain(action: Int) {          // fragment id not nav grpah
        Navigation.findNavController(this, R.id.fragmentMain).navigate(action, null, options)
    }


    fun replaceFragmentInAuth(action: Int) {          // fragment id not nav grpah
        Navigation.findNavController(this, R.id.authHostFragment).navigate(action, null, options)
    }

    fun replaceFragmentInAuth(action: Int, bundle: Bundle) {
        Navigation.findNavController(this, R.id.authHostFragment).navigate(action, bundle, options)
    }

    fun replaceAndRemoveFragmentInAuth(
        action1: Int,
        action2: Int,
        bundle: Bundle? = null,
        opt: NavOptions? = null
    ) {
        Navigation.findNavController(this, R.id.authHostFragment).popBackStack(action1, true)
        Navigation.findNavController(this, R.id.authHostFragment)
            .navigate(action2, bundle, opt?.let { opt } ?: kotlin.run { options })

    }


    fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } ?: kotlin.run {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun showSnackbar(view: View, message: String?) {
        message?.let {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        } ?: kotlin.run {
            Snackbar.make(view, getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT)
                .show()
        }

    }

    fun logoutFromApp(sharePref: SharePref) {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mGoogleSignInClient.signOut()

        sharePref.deleteAllSharedPrefs()
        deleteToken()

        runOnUiThread {
            showToast("Your account has been logged into another device.")
        }

        val intent: Intent = Intent(this, AuthActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }


}