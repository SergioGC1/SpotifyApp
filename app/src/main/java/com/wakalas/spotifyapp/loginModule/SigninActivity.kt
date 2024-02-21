package com.wakalas.spotifyapp.loginModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.entities.UserEntity
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.ActivitySigninBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

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
            signin()
        }

        mBinding.btnBack.setOnClickListener {
            goToLoginActivity()
        }
    }

    private fun signin()
    {
        val username = mBinding.etUser.text.toString().trim()
        val mail = mBinding.etMail.text.toString().trim()
        val passwd = mBinding.etPass.text.toString()

        val user = UserEntity(username = username, password = passwd, email = mail)
        Log.i("User", user.toString())
        postUser(user)
    }

    private fun goToLoginActivity()
    {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun postUser(userEntity: UserEntity) {
        lifecycleScope.launch {
            try {
                val result = RetrofitClient.userService.postUser(userEntity)

                val responseString = result.msg

                if (responseString.contains("correctamente")) {
                    showSnackbar(responseString)
                    goToLoginActivity()
                } else {
                    showSnackbar(responseString)
                }

            } catch (e: Exception) {
                showSnackbar(e.toString())
                Log.e("Error", e.toString())
            }
        }
    }
    private fun showSnackbar(string: String)
    {
        Snackbar.make(mBinding.root, string, Snackbar.LENGTH_LONG).show()
    }
}
