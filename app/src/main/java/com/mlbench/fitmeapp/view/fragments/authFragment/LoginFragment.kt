package com.mlbench.fitmeapp.view.fragments.authFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mlbench.fitmeapp.R
import com.mlbench.fitmeapp.base.BaseFragment
import com.mlbench.fitmeapp.base.Inflate
import com.mlbench.fitmeapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment :BaseFragment<FragmentLoginBinding>()
{
    override val inflate: Inflate<FragmentLoginBinding>
        get() = FragmentLoginBinding::inflate

    override fun observeLiveData() {
    }

    override fun init(savedInstanceState: Bundle?) {
    }

}