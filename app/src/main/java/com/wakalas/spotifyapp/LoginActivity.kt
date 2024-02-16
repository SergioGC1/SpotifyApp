package com.wakalas.spotifyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wakalas.spotifyapp.databinding.ActivityLoginBinding
import com.wakalas.spotifyapp.databinding.ActivityMainBinding
import com.wakalas.spotifyapp.mainModule.MainActivity

class LoginActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setListeners()
    }

    private fun setListeners()
    {
        mBinding.btnLogin.setOnClickListener {
            goToMainActivity()
        }
    }

    private fun goToMainActivity()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
