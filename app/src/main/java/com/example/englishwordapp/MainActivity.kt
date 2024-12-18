package com.example.englishwordapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import Dictionary.LearnWords
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


        //TODO возвращать словарь слов

        val dictionary = LearnWords()

        val x = dictionary.SmallDictionary()//Возвращает одно слово, а не несколько

        with(binding){

            val correctBlock: View = clCorrectBlock

            correctBlock.visibility = View.GONE

            for (i in 0 until llWordsList.childCount){

                Log.d("Dictionary Size", "Dictionary size_2 - ${x.size}")

                if (i == 3){//Если правильный индекс
                    val v: View = llWordsList.getChildAt(i)

                    val ll: LinearLayout = v as LinearLayout
                    ll.setOnClickListener {

                        btnSkipButton.visibility = View.INVISIBLE//Прячем кнопку SKIP

                        val number: TextView = ll.getChildAt(0) as TextView

                        val word: TextView = ll.getChildAt(1) as TextView

                        markAnswerCorrect(ll, number, word, correctBlock)
                    }
                }
                else{
                    val v: View = llWordsList.getChildAt(i)

                    val ll: LinearLayout = v as LinearLayout

                    ll.setOnClickListener{

                        btnSkipButton.visibility = View.INVISIBLE//Прячем кнопку SKIP

                        val number: TextView = ll.getChildAt(0) as TextView

                        val word: TextView = ll.getChildAt(1) as TextView

                        markAnswerUncorrect(ll, number, word, correctBlock)
                    }
                }
            }
        }
        //Нейтральный
    }

    private fun markAnswerCorrect(linerLayout: LinearLayout, number:TextView, word: TextView, correctBlock: View) { //Корректный ответ

        linerLayout.background = resources.getDrawable(R.drawable.right_ansor_values_shape)
        number.background = resources.getDrawable(R.drawable.right_ansor_squer)

        number.setTextColor(ContextCompat.getColor(this, R.color.white))
        word.setTextColor(ContextCompat.getColor(this, R.color.correct_ansor))

        correctBlock.visibility = View.VISIBLE//SKIP нужно скрывать

        Log.d("Correct", "This right ansor")
    }

    private fun markAnswerUncorrect(linerLayout: LinearLayout, number:TextView, word: TextView, correctBlock: View){ //Неверный ответ

        linerLayout.background = resources.getDrawable(R.drawable.wrong_ansor_values_shape)
        number.background = resources.getDrawable(R.drawable.wrong_ansor_squer)

        number.setTextColor(ContextCompat.getColor(this, R.color.white))
        word.setTextColor(ContextCompat.getColor(this, R.color.wrong_ansor_color_text))

        correctBlock.visibility = View.VISIBLE

        binding.ivIcCorrect.setImageResource(R.drawable.ic_wrong__1)

        binding.tvResult.text = resources.getText(R.string.wrong)
        binding.btnCorrectButton.setTextColor(ContextCompat.getColor(this, R.color.wrong_ansor_color_text))

        correctBlock.setBackgroundColor(ContextCompat.getColor(this, R.color.wrong_ansor))

        Log.d("Uncorrect", "This not right ansor")
    }
}