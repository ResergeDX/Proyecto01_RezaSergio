package com.example.proyecto1

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener:(day:Int, month:Int,year:Int)->Unit):
    DialogFragment(), DatePickerDialog.OnDateSetListener{

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        listener(day, month, year)
    }
    //Funcion para obtener el bloque de calendario para la fecha
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c       = Calendar.getInstance()
        val year    = c.get(Calendar.YEAR)
        val month   = c.get(Calendar.MONTH)
        val day     = c.get(Calendar.DAY_OF_MONTH)
        val picker  = DatePickerDialog(activity as Context,this, year, month, day)
        //Rango de 90 años atras a la fecha actual
        c.add(Calendar.YEAR,-90)
        picker.datePicker.minDate=c.timeInMillis
        c.add(Calendar.YEAR,+90)
        picker.datePicker.maxDate=c.timeInMillis
        return picker
    }

}