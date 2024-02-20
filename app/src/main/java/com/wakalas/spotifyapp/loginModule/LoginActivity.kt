package com.wakalas.spotifyapp.loginModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wakalas.spotifyapp.databinding.ActivityLoginBinding
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
            if(login())
            {
                goToMainActivity()
            }
        }

        mBinding.btnSignin.setOnClickListener {
            goToSigninActivity()
        }
    }

    private fun login(): Boolean
    {
        var accepted = false
        val username = mBinding.etUser.text.toString().trim()

        return true
    }

    private fun goToMainActivity()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goToSigninActivity()
    {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
    }
}
