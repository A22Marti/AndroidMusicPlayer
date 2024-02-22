package com.example.musicplayer

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(private val songList : List<Cancion>) : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    private lateinit var context : Context;
    inner class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Name: TextView = itemView.findViewById(R.id.songName)
        var Play: Button = itemView.findViewById(R.id.Play)
        var Stop : Button = itemView.findViewById(R.id.Stop)
        var Restart : Button = itemView.findViewById(R.id.Restart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context);
        val View = inflater.inflate(R.layout.cancion_layout,parent,false);
        return ViewHolder(View)
    }

    override fun getItemCount(): Int {
        return songList!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cancion: Cancion = songList!!.get(position)
        Log.d("Name",cancion.Name)
        val songName : TextView = holder.Name
        songName.text = cancion.Name
        holder.Name.text = cancion.Name;
        var playbackPosition: Int = 0
        val mediaPlayer = MediaPlayer.create(context, cancion.Path)
        mediaPlayer.setVolume(1f,1f)
        holder.Play.setOnClickListener {
            mediaPlayer?.apply {
                if (!isPlaying) {
                    if (playbackPosition == 0) {
                        reset()
                        val songResource = cancion.Path
                        setDataSource(context, Uri.parse("android.resource://${context.packageName}/$songResource"))
                        prepareAsync()
                        setOnPreparedListener { mp ->
                            mp.seekTo(playbackPosition)
                            mp.start()
                        }
                    } else {
                        seekTo(playbackPosition)
                        start()
                    }
                }
            }
        }

        holder.Stop.setOnClickListener {
            mediaPlayer?.apply {
                if (isPlaying) {
                    pause()
                    playbackPosition = currentPosition
                }
            }
        }

        holder.Restart.setOnClickListener {
            mediaPlayer?.apply {
                seekTo(0)
                playbackPosition = 0;
                start()
            }
        }
    }
}
