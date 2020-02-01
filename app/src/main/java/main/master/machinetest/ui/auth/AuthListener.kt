package main.master.machinetest.ui.auth


interface AuthListener {

    fun onStarted()

    fun onSuccess()


    fun onFailure(message:String)


}