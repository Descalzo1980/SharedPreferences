package ru.stas.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.stas.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.bntSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()
            val isAdult = binding.cbAdult.isChecked

            editor.apply {
                putString("name",name)
                putInt("age",age)
                putBoolean("isAdult",isAdult)
                apply()
            }
        }

        binding.btnLoad.setOnClickListener {
            val name = sharedPref.getString("name","Stas")
            val age = sharedPref.getInt("age",42)
            val isAdult = sharedPref.getBoolean("isAdult",true)

            binding.etName.setText(name)
            binding.etAge.setText(age.toString())
            binding.cbAdult.isChecked = isAdult
        }
    }
}