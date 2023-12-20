package com.example.submission_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCharacter: RecyclerView
    private val list = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        // Disable the title in the action bar
        val ab = supportActionBar
        ab?.setDisplayShowTitleEnabled(false)

        rvCharacter = findViewById(R.id.rv_character)
        rvCharacter.setHasFixedSize(true)

        list.addAll(getListCharacters())
        showRecyclerList()
    }

    private fun getListCharacters(): ArrayList<Character> {
        val dataName = resources.getStringArray(R.array.character_list)
        val dataDesc = resources.getStringArray(R.array.character_description)
        val dataPhoto = resources.getStringArray(R.array.character_photo)
        val listCharacter = ArrayList<Character>()
        for (i in dataName.indices) {
            val character = Character(dataName[i], dataDesc[i], dataPhoto[i])
            listCharacter.add(character)
        }
        return listCharacter
    }

    private fun showRecyclerList() {
        rvCharacter.layoutManager = LinearLayoutManager(this)
        val listCharacterAdapter = ListCharacterAdapter(list)
        rvCharacter.adapter = listCharacterAdapter

        listCharacterAdapter.setOnItemClickCallback(object : ListCharacterAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Character) {
                showSelectedCharacter(data)
            }
        })
    }

    private fun showSelectedCharacter(character: Character) {
        val intent = Intent(this@MainActivity, DetailPage::class.java)
        intent.putExtra("character_name", character.name)
        intent.putExtra("character_desc", character.description)
        intent.putExtra("character_photo", character.photo)
        startActivity(intent)
    }
}