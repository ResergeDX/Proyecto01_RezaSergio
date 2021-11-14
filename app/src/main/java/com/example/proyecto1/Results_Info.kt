package com.example.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Results_Info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_info)
        val bundle = intent.extras

        val nombreObt=bundle?.getString("Nombre","")
        val accountObt=bundle?.getInt("Cuenta",0)
        val birthObt=bundle?.getString("Birth","01/01/2000")
        val emailObt=bundle?.getString("Email","noemail@gmail.com")
        val zodiacObt=bundle?.getInt("Zodiac",1)
        val ageObt=bundle?.getInt("Edad",1)

    }
}