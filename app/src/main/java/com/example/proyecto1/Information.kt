package com.example.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Information : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etBirth: EditText
    private lateinit var etAccount: EditText
    private lateinit var etEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        etNombre=findViewById(R.id.etNombre)
        etBirth=findViewById(R.id.etBirth)
        etAccount=findViewById(R.id.etAccount)
        etEmail=findViewById(R.id.etEmail)
        etBirth.setOnClickListener{
            showDatePickerDialog()
        }

    }
    private fun showDatePickerDialog(){
        val datePicker=DatePickerFragment{day,month,year -> onDateSelected(day,month,year)}
        datePicker.show(supportFragmentManager,"datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        etBirth.setText(getString(R.string.BirthFormat,day,month,year))

    }

    fun verify_Name(name:EditText): Boolean{
        if (name.text.toString()!="") {
            return true
        }else{
            Toast.makeText(this, getString(R.string.ToastName) ,Toast.LENGTH_LONG).show()

            return false
        }
    }
    fun verify_Account(accountNumber: EditText):Boolean{
        if(Integer.parseInt(accountNumber.text.toString())>0){
           return true
        }else{
            Toast.makeText(this, getString(R.string.ToastAccount),Toast.LENGTH_LONG).show()

            return false
        }
    }

    //fun verify_birth():Boolean{}
    //fun verify_Email():Boolean{}
    fun obtain_Zodiac_Sign(dateBirth:String):Int{
        var fechaDate=SimpleDateFormat("dd/MM/yyyy").parse(dateBirth)
        var datosFecha=fechaDate.toString()
        val info=datosFecha.split(" ")
        Log.i("InfoFecha",info[5])
        var year=Integer.parseInt(info[5].toString())
        var horoscopo=(year%12)+1
        return horoscopo
    }
    fun obtainAge(dateBirth:String): Long{
        var fechaDate=SimpleDateFormat("dd/MM/yyyy").parse(dateBirth)

        var fechaActual=Date(System.currentTimeMillis())
        var dif=fechaActual.time-fechaDate.time
        Log.i("InfoFecha",fechaActual.time.toString())
        var seg=dif/1000
        var min=seg/60
        var hora=min/60
        var dia=hora/24
        var years:Long=dia/365
        Log.i("InfoFecha",years.toString())
        return years

    }

    fun revisarInfo(view: View) {
        var boolName:Boolean=verify_Name(etNombre)
        var boolAccount:Boolean=verify_Account(etAccount)
        var boolBirth:Boolean=true
        var boolEmail:Boolean=true

        val birthday = etBirth.text.toString()
        val email = etEmail.text.toString()
        if((boolName==true)and(boolAccount==true)and(boolBirth==true)and(boolEmail==true)) {
            val intent = Intent(this, Results_Info::class.java)
            val param=Bundle()
            param.putString("Nombre",etNombre.text.toString())
            param.putInt("Cuenta",Integer.parseInt(etAccount.text.toString()))
            param.putString("Birth",etBirth.text.toString())
            param.putString("Email",etEmail.text.toString())
            param.putInt("Zodiac",obtain_Zodiac_Sign(etBirth.text.toString()))
            param.putLong("Edad",obtainAge(etBirth.text.toString()))
            intent.putExtras(param)
            startActivity(intent)
        }else{
            Toast.makeText(this, getString(R.string.ToastAccount),Toast.LENGTH_LONG).show()
        }






    }
}