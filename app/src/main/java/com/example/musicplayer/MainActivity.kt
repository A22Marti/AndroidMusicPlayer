package com.example.musicplayer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var musicAdapter: MusicAdapter
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Crear las canciones
        val songs = ArrayList<Cancion>()
        val afterlife = Cancion("Bring Me To Life", R.raw.bring_me_to_life, "drawable")
        val secondsFromPanic = Cancion("Can You Feel My Heart", R.raw.can_you_feel_my_heart, "drawable")
        val mrBrightSide = Cancion("In The End", R.raw.in_the_end, "drawable")

        // Agregar las canciones a la lista
        songs.add(afterlife)
        songs.add(secondsFromPanic)
        songs.add(mrBrightSide)

        // Inicializar el adaptador
        musicAdapter = MusicAdapter(songs)

        val recyclerView = findViewById<RecyclerView>(R.id.songs)
        recyclerView.adapter = musicAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        // Log para verificar las canciones
        Log.d("Songs", songs.toString())
    }
}
