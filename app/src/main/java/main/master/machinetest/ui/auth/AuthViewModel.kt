package main.master.machinetest.ui.auth


import android.view.View
import androidx.lifecycle.ViewModel

//AuthViewModel binded in activity_login layout for email,password and button onclick  - onLoginButtonClick
class AuthViewModel : ViewModel(){

    var email:String  = "Test@gmail.com"
    var password: String = "1234"
    var authListener : AuthListener ?= null

    fun onLoginButtonClick(view : View){

        //listener will started which implemened in Login activity with toast
        authListener?.onStarted()

        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            //listener will failure message
            authListener?.onFailure("Invalid Email or Pasword")
            return
        }
        //if the above condition goes false then we have listener success
        authListener?.onSuccess()
    }
}