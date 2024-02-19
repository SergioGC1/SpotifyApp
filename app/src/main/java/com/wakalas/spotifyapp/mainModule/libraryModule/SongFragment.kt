package com.wakalas.spotifyapp.mainModule.libraryModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wakalas.spotifyapp.Application
import com.wakalas.spotifyapp.common.adapters.SongLibraryAdapter
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentSongBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SongFragment : Fragment()
{
    private lateinit var mBinding: FragmentSongBinding

    private lateinit var mSongAdapter: SongLibraryAdapter
    private lateinit var mLinearLayout: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        mBinding = FragmentSongBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView()
    {
        mSongAdapter = SongLibraryAdapter()
        mLinearLayout = LinearLayoutManager(requireContext())

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mSongAdapter
        }

        getSongs()
    }

    private fun getSongs()
    {
        val playlist = Application.playlist
        mBinding.tvPlaylist.text = playlist.titulo

        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.songService.getSongsPlaylist(playlist.id)
                val songs = result.body()

                withContext(Dispatchers.Main) {
                    mSongAdapter.submitList(songs)
                }
            }
            catch(e: Exception)
            {
                Log.e("ERROR", "$e")
            }
        }
    }
}
