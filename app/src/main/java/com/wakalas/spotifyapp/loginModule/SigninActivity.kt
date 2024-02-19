package com.wakalas.spotifyapp.loginModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setListeners()
    }

    private fun setListeners()
    {
        mBinding.btnSignin.setOnClickListener {
            goToLoginActivity()
        }

        mBinding.btnBack.setOnClickListener {
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity()
    {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
