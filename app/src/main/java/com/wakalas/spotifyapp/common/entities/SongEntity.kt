package com.wakalas.spotifyapp.common.entities

data class SongEntity(
    var id: Long,
    var titulo: String,
    var duracion: Int,
    var ruta: String,
    var numeroReproducciones: Long
)
{
    fun duracionFormat(): String
    {
        val minutes = duracion / 60
        val seconds = duracion % 60
        var secondsString = seconds.toString()

        if(seconds < 10)
        {
            secondsString = "0$seconds"
        }

        return "$minutes:$secondsString"
    }
}
