package com.example.englishwordapp

import Dictionary.LearnWords
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.englishwordapp.databinding.ActivityLearnWordBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //TODO теперь с поворотом всё верно, но у меня теперь проблемы с индексами, выбирая верное слово он не всегда возвращает верный ответ.
    // 3 Ночной режим
    // 4 оптимизация
    // 5 Сериализация слов или прикрутка к API
    // 6 Регистрация
    // 7 Другие режимы

    private var questionWord: String = ""

    private var qusetionWordInRussian: String = ""

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding have null")

    val dictionary = LearnWords()

    var dictionary_four = dictionary.SmallDictionary()//Нормальный словарь вернулся.


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

        val ivCloseButton: ImageView = findViewById(R.id.ib_close_button)

        ivCloseButton.setOnClickListener{
            System.exit(0)//На телефоне Закрывается
        }

        with(binding){

            val correctBlock: View = clCorrectBlock

            correctBlock.visibility = View.GONE

            fillInTheOptions() // Всё красиво заполняються поля.

            setScore()

            CheckRightOrNot(correctBlock)
        }
    }
    private fun  setScore(){
        val arr: Array<Int> = dictionary.returnCountWords()

        binding.tvScore.text = "${arr[0].toString()} / ${arr[1].toString()}"
    }

    private fun CheckRightOrNot(correctBlock: View){


        val list: List<LinearLayout> = listOf(binding.llFirstBlock, binding.llSecondBlock, binding.llThirdBlock, binding.llFourBlock)


        for (i in 0 until list.size){

                list[i].setOnClickListener {

                    for (j in 0 until list.size){
                        list[j].isEnabled = false
                    }

                    val textStr: TextView = list[i].getChildAt(1) as TextView

                    val strOnRussian : String = textStr.text.toString()
                    //dictionary_four.get(strOnRussian)
                    if (qusetionWordInRussian == strOnRussian){

                        Log.d("Result_Game_WOW", "${qusetionWordInRussian}  |||||| ${strOnRussian}")
                        binding.btnSkipButton.visibility = View.INVISIBLE

                        val number: TextView = list[i].getChildAt(0) as TextView

                        val word: TextView = list[i].getChildAt(1) as TextView

                        dictionary.setLearningWord(strOnRussian)

                        markAnswerCorrect(list[i], number, word, correctBlock)
                    }

                    else{
                        Log.d("Result_Game_WOW", "${qusetionWordInRussian}  |||||| ${strOnRussian}")
                        binding.btnSkipButton.visibility = View.INVISIBLE

                        val number: TextView = list[i].getChildAt(0) as TextView

                        val word: TextView = list[i].getChildAt(1) as TextView

                        markAnswerUncorrect(list[i], number, word, correctBlock)
                    }
                }

        }
    }

    private fun fillInTheOptions(){

        val tvQuestWord: TextView = binding.tvGivenWord

        tvQuestWord.text = dictionary_four.values.toList().get(Random.nextInt(dictionary_four.size))

        val keyList = dictionary_four.keys.toList()

        //Длинное решение
        val tv1: TextView = binding.llFirstBlock.getChildAt(1) as TextView

        tv1.text = keyList[0]

        val tv2: TextView = binding.llSecondBlock.getChildAt(1) as TextView

        tv2.text = keyList[1]

        val tv3: TextView = binding.llThirdBlock.getChildAt(1) as TextView

        tv3.text = keyList[2]

        val tv4: TextView = binding.llFourBlock.getChildAt(1) as TextView

        tv4.text = keyList[3]
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

    override fun onSaveInstanceState(outState: Bundle) {
        //Теперь всё падает
        super.onSaveInstanceState(outState)

        val firstBlcok = binding.llFirstBlock.getChildAt(1) as TextView

        val firstword: String = firstBlcok.text.toString()

        val secondBlock = binding.llSecondBlock.getChildAt(1) as TextView

        val secondWord: String = secondBlock.text.toString()

        val thirdBlock = binding.llThirdBlock.getChildAt(1) as TextView

        val thirdWord: String = thirdBlock.text.toString()

        val fourBlock = binding.llFourBlock.getChildAt(1) as TextView

        val fourWord: String = fourBlock.text.toString()


        outState.putString("firstBlockWord", firstword)
        outState.putString("secondBlockWord",secondWord)
        outState.putString("thirdBlockWord", thirdWord)
        outState.putString("fourBlockWord", fourWord)


        //TODO Новый вариант плохой, просто пока вернуть к предыдущему варианту

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val first: String = savedInstanceState.getString("firstBlockWord","")
        val second: String = savedInstanceState.getString("secondBlockWord", "")
        val third: String = savedInstanceState.getString("thirdBlockWord","")
        val four: String = savedInstanceState.getString("fourBlockWord", "")


        val tv1 = binding.llFirstBlock.getChildAt(1) as TextView
        val tv2 = binding.llSecondBlock.getChildAt(1) as TextView
        val tv3 = binding.llThirdBlock.getChildAt(1) as TextView
        val tv4 = binding.llFourBlock.getChildAt(1) as TextView


        tv1.text = first
        tv2.text = second
        tv3.text = third
        tv4.text = four

    }
}

