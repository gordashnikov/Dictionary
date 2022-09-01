package com.rustyrobot.dictionary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rustyrobot.dictionary.MainActivity.Companion.KEY
import com.rustyrobot.dictionary.databinding.ActivityDefinitionBinding

class DefinitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDefinitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefinitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.definitionTextVew.text = intent.getStringExtra(KEY)
        binding.backImageView.setOnClickListener { finish() }
    }
}