package com.wakalas.spotifyapp.mainModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wakalas.spotifyapp.Application
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.adapters.AlbumAdapter
import com.wakalas.spotifyapp.common.adapters.PlaylistHomeAdapter
import com.wakalas.spotifyapp.common.adapters.PodcastAdapter
import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.common.listeners.PlaylistListener
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), PlaylistListener
{
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mPlaylistAdapter: PlaylistHomeAdapter
    private lateinit var mPodcastAdapter: PodcastAdapter
    private lateinit var mAlbumAdapter: AlbumAdapter
    private lateinit var mLinearLayout: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerViews()
    }

    private fun setUpRecyclerViews(){

        playlistsRecyclerView()
        podcastsRecyclerView()
        albumRecyclerView()
    }


    private fun playlistsRecyclerView()
    {
        mPlaylistAdapter = PlaylistHomeAdapter(this)
        mLinearLayout = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        mBinding.recyclerPlaylist.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mPlaylistAdapter
        }

        getPlaylists()
    }


    private fun podcastsRecyclerView()
    {
        mPodcastAdapter = PodcastAdapter()
        mLinearLayout = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        mBinding.recyclerPodcast.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mPodcastAdapter
        }

        getPodcasts()
    }

    private fun albumRecyclerView()
    {
        mAlbumAdapter = AlbumAdapter()
        mLinearLayout = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        mBinding.recyclerAlbum.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mAlbumAdapter
        }
        getAlbums()
    }

    private fun getPlaylists()
    {
        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.playlistService.getPlaylistByUser(Application.user.id)
                Log.i("Playlists",RetrofitClient.playlistService.getPlaylistByUser(Application.user.id).toString())
                val playlists = result.body()!!

                withContext(Dispatchers.Main)
                {
                    Log.i("Playlists",playlists.toString())
                    mPlaylistAdapter.submitList(playlists)
                }
            } catch (e: Exception)
            {
                showSnackbar(e.toString())
            }
        }
    }
    private fun getPodcasts()
    {
        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.podcastService.getPodcasts(Application.user.id)

                val podcasts = result.body()!!

                withContext(Dispatchers.Main)
                {
                    mPodcastAdapter.submitList(podcasts)
                }
            } catch (e: Exception)
            {
                showSnackbar(e.toString())
            }
        }
    }

    private  fun getAlbums(){
        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.albumService.getAlbumsByUser(Application.user.id)

                val albums = result.body()!!

                withContext(Dispatchers.Main)
                {
                    mAlbumAdapter.submitList(albums)
                }
            }catch (e: Exception)
            {
                showSnackbar(e.toString())
            }
        }
    }

    private fun showSnackbar(string: String)
    {
        Snackbar.make(mBinding.root, string, Snackbar.LENGTH_LONG).show()
    }

    override fun onClick(playlistEntity: PlaylistEntity)
    {
        Application.playlist = playlistEntity
        goToSongFragment()
    }

    private fun goToSongFragment()
    {
        findNavController().navigate(R.id.action_homeFragment_to_songFragment)
    }
}
