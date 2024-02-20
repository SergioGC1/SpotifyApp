package com.wakalas.spotifyapp.mainModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wakalas.spotifyapp.common.adapters.SongAdapter
import com.wakalas.spotifyapp.common.entities.SongEntity
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentFindBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FindFragment : Fragment() {
    private lateinit var mBinding: FragmentFindBinding
    private lateinit var mSongAdapter: SongAdapter
    private lateinit var mSongList: List<SongEntity>
    private lateinit var mLinearLayout: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFindBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songsRecyclerView()
        setupSearchEditText()
    }

    private fun songsRecyclerView()
    {
        mSongAdapter = SongAdapter()
        mLinearLayout = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        mBinding.recyclerViewSearchResults.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mSongAdapter
        }

        getSongs()
    }

    private fun setupSearchEditText() {
        mBinding.findSong.addTextChangedListener { text ->
            val searchText = text.toString().trim()
            filterSongs(searchText)
        }
    }

    private fun getSongs() {
        lifecycleScope.launch {
            try {
                val result = RetrofitClient.songService.getSongs()

                val songs = result.body()!!

                withContext(Dispatchers.Main) {
                    Log.i("CANCIONES", songs.toString())
                    mSongList = songs // Actualiza la lista de canciones
                    mSongAdapter.submitList(songs)
                }
            } catch (e: Exception) {
                showSnackbar(e.toString())
            }
        }
    }

    private fun filterSongs(searchText: String) {
        if (::mSongList.isInitialized) {
            val filteredSongs = mSongList.filter { song ->
                song.titulo.contains(searchText, ignoreCase = true)
            }
            mSongAdapter.submitList(filteredSongs)
        }
    }


    private fun showSnackbar(string: String)
    {
        Snackbar.make(mBinding.root, string, Snackbar.LENGTH_LONG).show()
    }
}
