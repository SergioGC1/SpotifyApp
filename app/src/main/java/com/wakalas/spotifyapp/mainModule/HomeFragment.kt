package com.wakalas.spotifyapp.mainModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wakalas.spotifyapp.common.adapters.PlaylistHomeAdapter
import com.wakalas.spotifyapp.common.adapters.PodcastAdapter
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment()
{
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mPlaylistAdapter: PlaylistHomeAdapter
    private lateinit var mPodcastAdapter: PodcastAdapter
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

        Log.i("Playlists " , mPlaylistAdapter.toString())
    }

    private fun setUpRecyclerViews(){

        playlistsRecyclerView()
        podcastsRecyclerView()
    }



    private fun playlistsRecyclerView()
    {
        mPlaylistAdapter = PlaylistHomeAdapter()
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

    private fun getPlaylists()
    {
        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.playlistService.getPlaylists()

                val playlists = result.body()!!

                withContext(Dispatchers.Main)
                {
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
                val result = RetrofitClient.podcastService.getPodcasts()

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



    private fun showSnackbar(string: String)
    {
        Snackbar.make(mBinding.root, string, Snackbar.LENGTH_LONG).show()
    }
}