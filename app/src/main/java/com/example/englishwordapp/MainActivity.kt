package com.example.englishwordapp

import android.os.Bundle
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
            ibCloseButton.isVisible = false
            tvGivenWord.text = "fksjdf"
        }//урок 8

    }
}