package com.luthfir.examplelogin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.luthfir.examplelogin.databinding.ActivityMainBinding
import com.luthfir.examplelogin.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObserver()
        initListener()

    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun initObserver() {

        viewModel.getLoginStatus(this)

        viewModel.loginStatus.observe(this) { loginStatus ->
            if (!loginStatus) {
                initLogout()
            }
        }
    }

    private fun initListener() {

        binding.btnLogout.setOnClickListener {
            viewModel.setLoginStatus(this@MainActivity, false)
            initLogout()
        }
    }

    private fun initLogout() {
        val intentLogin = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intentLogin)
        finish()
    }

}