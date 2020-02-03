package main.master.machinetest.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import main.master.machinetest.R
import main.master.machinetest.databinding.ActivityLoginBinding
import main.master.machinetest.ui.home.WeatherActivity
import main.master.machinetest.utils.toast


class LoginActivity : AppCompatActivity(),AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding the R.layout.activity_login with databinding
        var binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        //AuthViewModel which has our data
        var viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        //binidng viewmodel
        binding.viewmodel = viewmodel
        //listener
        viewmodel.authListener = this
    }

    override fun onStarted() {
        toast("LoginStarted")
    }

    override fun onSuccess() {
        toast("LoginSuccess")
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}
