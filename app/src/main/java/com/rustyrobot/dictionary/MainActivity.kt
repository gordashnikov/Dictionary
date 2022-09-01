package com.rustyrobot.dictionary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.rustyrobot.dictionary.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queue = Volley.newRequestQueue(this)

        binding.findButton.setOnClickListener {
            val url = getUrl()
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                try {
                    extractDefinitionFromJson(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }, { error ->
                error
            })
            queue.add(stringRequest)

        }
    }

    private fun getUrl(): String {
        val word = binding.wordEditText.text
        val apiKey = "577c2a69-92a2-457d-91ba-2833b80650e4"
        return "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)

    }

    companion object {
        const val KEY = "WORD_DEFINITION"
    }
}