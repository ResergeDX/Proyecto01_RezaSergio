package com.example.proyecto1


import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Results_Info : AppCompatActivity() {
    private lateinit var tvNombre: TextView
    private lateinit var tvBirth: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvZodiac: TextView
    private lateinit var tvAccount: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ivZodiacAnimal: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_info)
        val bundle = intent.extras

        val nombreObt=bundle?.getString("Nombre","")
        val accountObt=bundle?.getInt("Cuenta",0)
        val birthObt=bundle?.getString("Birth","01/01/2000")
        val emailObt=bundle?.getString("Email","noemail@gmail.com")
        val zodiacObt=bundle?.getInt("Zodiac",1)
        val ageObt=bundle?.getLong("Edad",1)


        tvNombre    =   findViewById(R.id.idSaludoUser)
        tvBirth     =   findViewById(R.id.tvDate)
        tvAge       =   findViewById(R.id.tvYears)
        tvZodiac    =   findViewById(R.id.tvZodiac)
        tvAccount   =   findViewById(R.id.tvCuenta)
        tvEmail     =   findViewById(R.id.tvCorreo)
        ivZodiacAnimal= findViewById(R.id.ivZodiacAnimal)

        tvNombre.text=getString(R.string.greetUser,nombreObt)
        tvBirth.text=birthObt

        tvZodiac.text=zodiacSign(zodiacObt)
        tvAccount.text=accountObt.toString()
        tvEmail.text=emailObt

        zodiacAnimal(ivZodiacAnimal,zodiacObt)

        if(ageObt?.toInt()==1){
            tvAge.text=getString(R.string.Age1Space)
        }else{
            tvAge.text=getString(R.string.AgeSpace,ageObt)
        }

    }
    //Asigna el String del Signo del Zodiaco Chino Obtenido
    private fun zodiacSign(signo:Int?):String{
        when (signo){
            1->{return getString(R.string.ZodiacMonkey)}
            2->{return getString(R.string.ZodiacRooster)}
            3->{return getString(R.string.ZodiacDog)}
            4->{return getString(R.string.ZodiacPig)}
            5->{return getString(R.string.ZodiacRat)}
            6->{return getString(R.string.ZodiacOx)}
            7->{return getString(R.string.ZodiacTiger)}
            8->{return getString(R.string.ZodiacRabbit)}
            9->{return getString(R.string.ZodiacDragon)}
            10->{return getString(R.string.ZodiacSnake)}
            11->{return getString(R.string.ZodiacHorse)}
            else->{return getString(R.string.ZodiacGoat)}
        }
    }
    //Asigna la imagen del animal correspondiente al signo del zodiaco en el ImageView
    private fun zodiacAnimal(iv:ImageView,signo:Int?){
        when (signo){
            1->{return iv.setImageResource(R.drawable.mono)}
            2->{return iv.setImageResource(R.drawable.gallo)}
            3->{return iv.setImageResource(R.drawable.perro)}
            4->{return iv.setImageResource(R.drawable.cerdo)}
            5->{return iv.setImageResource(R.drawable.rata)}
            6->{return iv.setImageResource(R.drawable.buey)}
            7->{return iv.setImageResource(R.drawable.tigre)}
            8->{return iv.setImageResource(R.drawable.conejo)}
            9->{return iv.setImageResource(R.drawable.dragon)}
            10->{return iv.setImageResource(R.drawable.serpiente)}
            11->{return iv.setImageResource(R.drawable.caballo)}
            else->{return iv.setImageResource(R.drawable.cabra)}
        }
    }

}