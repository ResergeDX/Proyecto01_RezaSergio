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
import java.util.regex.Pattern

class Information : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etBirth: EditText
    private lateinit var etAccount: EditText
    private lateinit var etEmail: EditText
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
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
    fun validEmail(email:String):Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun verifyInfo(): Boolean {
        var checkName:Boolean
        var checkBirth:Boolean
        var checkAccount:Boolean
        var checkEmail:Boolean
        //Revisión del nombre
        if (etNombre.text.toString() != "") {
            checkName=true
        } else {
            etNombre.error=getString(R.string.ToastName)
            checkName=false
        }
        //Revisión de la fecha de nacimiento
        if(etBirth.text.toString()!=""){
            //Revisión de fecha anterior al dia actual
            var fechaDate=SimpleDateFormat("dd/MM/yyyy").parse(etBirth.text.toString())
            var fechaActual=Date(System.currentTimeMillis())
            var dif=fechaActual.time-fechaDate.time
            if(dif>0){
                checkBirth=true
            }else{
                etBirth.error=getString(R.string.ToastBirth)
                checkBirth=false
            }

        }else{
            etBirth.error=getString(R.string.ToastBirth)
            checkBirth=false
        }
        //Revisa si existe numero de cuenta
        if (etAccount.text.toString()!=""){
            //Revisa si hay 9 digitos en el número de cuenta
            if(etAccount.text.toString().length==9){
                checkAccount=true
            }else{
                etAccount.error= getString(R.string.ToastAccountLength)
                checkAccount=false
            }
        }else{
            etAccount.error=getString(R.string.ToastAccount)
            checkAccount=false
        }
        //Revisa si hay correo
        if(etEmail.text.toString()!=""){
            //Revisa el formato de correo
            if(validEmail(etEmail.text.toString())){
                checkEmail=true
            }else{
                etEmail.error=getString(R.string.ToastEmailValid)
                checkEmail=false
            }
        }else{
            etEmail.error= getString(R.string.ToastEmail)
            checkEmail=false
        }
        //Revisión de los 4 puntos
        if((checkName)and(checkAccount)and(checkBirth)and(checkEmail)){
            return true
        }
        return false

    }
    //Obtención de indice para el signo del zodiaco chino
    fun obtain_Zodiac_Sign(dateBirth:String):Int{
        var fechaDate=SimpleDateFormat("dd/MM/yyyy").parse(dateBirth)
        var datosFecha=fechaDate.toString()
        val info=datosFecha.split(" ")
        Log.i("InfoFecha",info[5])
        var year=Integer.parseInt(info[5])
        var horoscopo=(year%12)+1
        return horoscopo
    }
    //Obtención de la edad por conversion de mili segundos a años
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
        //Si la información es correcta se enviará la información a la actividad de resultados
        if(verifyInfo()) {
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
            Toast.makeText(this, getString(R.string.InforVerify),Toast.LENGTH_LONG).show()
        }






    }
}