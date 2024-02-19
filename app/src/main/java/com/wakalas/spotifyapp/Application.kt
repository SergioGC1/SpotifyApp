package com.wakalas.spotifyapp

import android.app.Application
import com.wakalas.spotifyapp.common.entities.PlaylistEntity

class Application: Application()
{
    companion object
    {
        lateinit var playlist: PlaylistEntity
        val images = arrayOf(
            "https://www.thisisdig.com/wp-content/uploads/2021/06/californiacation-812x609.jpg",
            "https://i.ebayimg.com/images/g/sBQAAOSwgz5gx9Gu/s-l1600.jpg",
            "https://i.discogs.com/BKAhgCETKOO3L3FhPnOyfEtjwi2HTdjyQzvm5YIe3rk/rs:fit/g:sm/q:40/h:300/w:300/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTM3MjE0/NzktMTY0OTQ5NDg5/Ny05MDc4LmpwZWc.jpeg",
            "https://highclouds.org/wp-content/uploads/2023/11/dua-lipa-houdini-review.jpg",
            "https://i.discogs.com/MoQ1r1WE118flIFw1qS9GchYl8QjNBrGYhQrvTugksk/rs:fit/g:sm/q:90/h:594/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIwMTgz/NTQ4LTE2NDU5ODMz/NTAtNjE2NC5qcGVn.jpeg",
            "https://cdn.aniplaylist.com/thumbnails/0d0640295f747e7d546ab8e60b083d983c5bef21.jpeg"
        )
    }
}
