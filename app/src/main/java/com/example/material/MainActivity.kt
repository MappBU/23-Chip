package com.example.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.material.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),View.OnClickListener {

    var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Обработка табов по 3 кнопкам
        binding?.butRostov?.setOnClickListener(this)
        binding?.butLos?.setOnClickListener(this)
        binding?.butYork?.setOnClickListener(this)

    }

    override fun onClick(view: View) {

        // Для наполнения объекта башлоном item
        val inflater = LayoutInflater.from(this@MainActivity)
        // Наполняем
        val chip = inflater.inflate(R.layout.item, null, false) as Chip
        // Нажатие на кнопку удаления чипа
        chip.setOnCloseIconClickListener { v -> binding?.group?.removeView(v) }

        // Добавление Чипа при нажатии на кнопку
        when(view.id) {
            R.id.butRostov -> {
                // Добавляем на чип - название кнопки
                chip.text = binding?.butRostov?.text
                // создаем чип
                binding?.group?.addView(chip)
            }

            R.id.butLos -> {
                chip.text = binding?.butLos?.text
                binding?.group?.addView(chip)
            }

            R.id.butYork -> {
                chip.text = binding?.butYork?.text
                binding?.group?.addView(chip)
            }
        }

        // Показываем снейк бар при выборе чипа
        chip.setOnCheckedChangeListener { chip, isChecked ->
            // Здесь будет значение текста снейкбара
            var message:String? = null

            message = if (isChecked) selected()
            else noSelected()

            Snackbar.make(chip, message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor (this, R.color.orange))
                .setTextColor(ContextCompat.getColor (this, R.color.black))
                .show()

        }
    }

    private fun selected ():String {return getString(R.string.selected)}
    private fun noSelected ():String {return getString(R.string.noSelected)}

}