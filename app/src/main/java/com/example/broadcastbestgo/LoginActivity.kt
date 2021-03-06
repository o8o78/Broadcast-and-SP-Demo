package com.example.broadcastbestgo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if(isRemember){
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", " ")
            account_edit.setText(account)
            password_edit.setText(password)
            rememberPass.isChecked = true
        }


        loginButton.setOnClickListener{
            val account = account_edit.text.toString()
            val password = password_edit.text.toString()
            if(account=="admin" && password=="123456"){
                val editor = prefs.edit()
                if(rememberPass.isChecked){
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                }else{
                    editor.clear()
                }
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"invalid input, please try again.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}