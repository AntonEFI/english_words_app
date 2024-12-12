package com.example.englishwordapp

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.englishwordapp.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding have null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            llFirstBlock.setOnClickListener{

                val firstBlock : LinearLayout = llFirstBlock
                val firstBlockNumber: TextView = findViewById(R.id.tv_Variant_number_1)
                val firstBlcokText: TextView = findViewById(R.id.tv_Variant_text_1)

                markAnswerUncorrect(firstBlock, firstBlockNumber, firstBlcokText)
                //Нужно брать LinearLayout
                //tv_Variant_number_1
                //tv_Variant_text_1, а может вообще слово
            }
            llSecondBlock.setOnClickListener{
                val secondBlock : LinearLayout = llSecondBlock
                val secondBlockNumber: TextView = findViewById(R.id.tv_Variant_number_2)
                val secondBlcokText: TextView = findViewById(R.id.tv_Variant_text_2)

                markAnswerUncorrect(secondBlock, secondBlockNumber, secondBlcokText)
            }
            llThirdBlock.setOnClickListener{

                val thirdBlock : LinearLayout = llThirdBlock
                val thirdBlockNumber: TextView = findViewById(R.id.tv_Variant_number_3)
                val thirdBlcokText: TextView = findViewById(R.id.tv_Variant_text_3)

                markAnswerUncorrect(thirdBlock,thirdBlockNumber, thirdBlcokText)
            }
            llFourBlock.setOnClickListener {

                val fourBlock : LinearLayout = llFourBlock
                val fourBlockNumber: TextView = findViewById(R.id.tv_Variant_number_4)
                val fourBlcokText: TextView = findViewById(R.id.tv_Variant_text_4)

                markAnswerCorrect(fourBlock, fourBlockNumber, fourBlcokText)
            }
        }
        //Нейтральный
    }
    //В любом случае мне нужно в методы отправлять два TextView и менять им стили
    //Сейчас получаеться отстой потому-что в случае верного ответа. Мне нужно переключать стили
    private fun markAnswerCorrect(linerLayout: LinearLayout, number:TextView, word: TextView) { //Корректный ответ
        linerLayout.background = resources.getDrawable(R.drawable.right_ansor_values_shape)
        //number.style = resources. //Как задать стиль через код
        Log.d("Correct", "This right ansor")
    }

    //Сейчас получаеться отстой потому-что в случае неверного ответа. Мне нужно переключать стили
    private fun markAnswerUncorrect(linerLayout: LinearLayout, number:TextView, word: TextView){ //Неверный ответ
        Log.d("Uncorrect", "This not right ansor")
    }
}