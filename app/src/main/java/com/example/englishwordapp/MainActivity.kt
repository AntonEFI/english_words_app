package com.example.englishwordapp

import Dictionary.LearnWords
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import Dictionary.LearnWords.Companion
import android.content.Intent
import android.widget.Button
import com.example.englishwordapp.databinding.ActivityLearnWordBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //TODO 1 кнопка закрыть закрывает приложение 2 Горизонтальный вид 3 Ночной режим 4 оптимизация 5 Регистрация 6 Сериализация слов
    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding have null")

    val dictionary = LearnWords()

    val dictionary_four = dictionary.SmallDictionary()//Нормальный словарь вернулся.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val correctButton: Button = binding.btnCorrectButton

        val skipButton: Button = binding.btnSkipButton

        correctButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        skipButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
        //Проверка правильности можно перенести в отдельный метод
        //Короче работает, лучше чем я думал.
        with(binding){

            val correctBlock: View = clCorrectBlock

            correctBlock.visibility = View.GONE

            fillInTheOptions() // Всё красиво заполняються поля.

            setScore()

            CheckRightOrNot(llWordsList, correctBlock)
        }
    }
    private fun  setScore(){//Жалуется
        val arr: Array<Int> = dictionary.returnCountWords()

        binding.tvScore.text = "${arr[0].toString()} / ${arr[1].toString()}"
    }
    //
    private fun CheckRightOrNot(linerLayout: LinearLayout, correctBlock: View){
        for (i in 0 until linerLayout.childCount){

            val llBlock: LinearLayout = linerLayout.getChildAt(i) as LinearLayout

            llBlock.setOnClickListener {

                val textStr: TextView = llBlock.getChildAt(1) as TextView

                val strOnRussian : String = textStr.text.toString() //По ключу полуаем значение //Переменная для хранения строки из нажатой TextView

                if (dictionary_four.get(strOnRussian) == binding.tvGivenWord.text.toString())//Получаю загаданное слово
                {

                    binding.btnSkipButton.visibility = View.INVISIBLE//Прячем кнопку SKIP

                    val number: TextView = llBlock.getChildAt(0) as TextView

                    val word: TextView = llBlock.getChildAt(1) as TextView

                    dictionary.setLearningWord(strOnRussian)

                    markAnswerCorrect(llBlock, number, word, correctBlock)
                }

                else {

                    binding.btnSkipButton.visibility = View.INVISIBLE//Прячем кнопку SKIP

                    val number: TextView = llBlock.getChildAt(0) as TextView

                    val word: TextView = llBlock.getChildAt(1) as TextView

                    markAnswerUncorrect(llBlock, number, word, correctBlock)

                }

            }

        }
    }

    private fun fillInTheOptions(){

        val tvQuestWord: TextView = binding.tvGivenWord

        tvQuestWord.text = dictionary_four.values.toList().get(Random.nextInt(dictionary_four.size))

        val keyList = dictionary_four.keys.toList()

        for (i in 0 until dictionary_four.size){

            val wordVariant = (binding.llWordsList.getChildAt(i) as LinearLayout).getChildAt(1) as TextView

            wordVariant.text = keyList[i]
        }
    }

    private fun markAnswerCorrect(linerLayout: LinearLayout, number:TextView, word: TextView, correctBlock: View) { //Корректный ответ

        linerLayout.background = resources.getDrawable(R.drawable.right_ansor_values_shape)
        number.background = resources.getDrawable(R.drawable.right_ansor_squer)

        number.setTextColor(ContextCompat.getColor(this, R.color.white))
        word.setTextColor(ContextCompat.getColor(this, R.color.correct_ansor))

        correctBlock.visibility = View.VISIBLE//SKIP нужно скрывать
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
    }
}