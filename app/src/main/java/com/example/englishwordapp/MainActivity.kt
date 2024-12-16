package com.example.englishwordapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.get
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


        //TODO Всё ещё упростить этот код. и прятать или показывать Correct и вот всё это .по умолчанию SKIP

        with(binding){
            for (i in 0 until llWordsList.childCount){

                if (i == 3){//Если правильный индекс
                    val v: View = llWordsList.getChildAt(i)

                    val ll: LinearLayout = v as LinearLayout
                    ll.setOnClickListener {

                        val num: View? = ll.getChildAt(0)

                        val number: TextView = num as TextView

                        val text: View? = ll.getChildAt(1)

                        val word: TextView = text as TextView

                        markAnswerCorrect(ll, number, word)
                    }
                }
                else{
                    val v: View = llWordsList.getChildAt(i)

                    val ll: LinearLayout = v as LinearLayout

                    ll.setOnClickListener{
//
                        val num: View? = ll.getChildAt(0)

                        val number: TextView = num as TextView

                        val text: View? = ll.getChildAt(1)

                        val word: TextView = text as TextView

                        markAnswerUncorrect(ll, number, word)
                    }
                }
            }
        }
        //Нейтральный
    }
    //В любом случае мне нужно в методы отправлять два TextView и менять им стили
    //Сейчас получаеться отстой потому-что в случае верного ответа. Мне нужно переключать стили
    private fun markAnswerCorrect(linerLayout: LinearLayout, number:TextView, word: TextView) { //Корректный ответ

        linerLayout.background = resources.getDrawable(R.drawable.right_ansor_values_shape)
        number.background = resources.getDrawable(R.drawable.right_ansor_squer)

        number.setTextColor(ContextCompat.getColor(this, R.color.white))
        word.setTextColor(ContextCompat.getColor(this, R.color.correct_ansor))

        Log.d("Correct", "This right ansor")
    }

    //Сейчас получаеться отстой потому-что в случае неверного ответа. Мне нужно переключать стили
    private fun markAnswerUncorrect(linerLayout: LinearLayout, number:TextView, word: TextView){ //Неверный ответ

        linerLayout.background = resources.getDrawable(R.drawable.wrong_ansor_values_shape)
        number.background = resources.getDrawable(R.drawable.wrong_ansor_squer)

        number.setTextColor(ContextCompat.getColor(this, R.color.white))
        word.setTextColor(ContextCompat.getColor(this, R.color.wrong_ansor_color_text))

        Log.d("Uncorrect", "This not right ansor")
    }
}