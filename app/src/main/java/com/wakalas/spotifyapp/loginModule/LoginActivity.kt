package com.wakalas.spotifyapp.loginModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.wakalas.spotifyapp.Application
import com.wakalas.spotifyapp.common.entities.UserEntity
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.ActivityLoginBinding
import com.wakalas.spotifyapp.mainModule.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding

    private var mUser: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setListeners()
    }

    private fun setListeners() {
        mBinding.btnLogin.setOnClickListener {
            login()
        }

        mBinding.btnSignin.setOnClickListener {
            goToSigninActivity()
        }
    }

    private fun verifyUser()
    {
        val passwd = mBinding.etPass.text.toString()

        if(mUser != null)
        {
            if (mUser!!.password == passwd)
            {
                Application.user = mUser!!
                goToMainActivity()
            }
            else
            {
                showSnackbar("Contraseña incorrecta")
            }
        }
    }

    private fun login()
    {
        val username = mBinding.etUser.text.toString().trim()

        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.userService.getUser(username)
                mUser = result.body()!!

                withContext(Dispatchers.Main)
                {
                    verifyUser()
                }
            }
            catch(e: Exception)
            {
                withContext(Dispatchers.Main)
                {
                    showSnackbar("El usuario $username no ha sido creado")
                }
            }
        }
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

    private fun showSnackbar(string: String)
    {
        Snackbar.make(mBinding.root, string, Snackbar.LENGTH_LONG).show()
    }
}
