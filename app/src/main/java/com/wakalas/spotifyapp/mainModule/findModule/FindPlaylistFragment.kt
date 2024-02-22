package com.wakalas.spotifyapp.mainModule.findModule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wakalas.spotifyapp.Application
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.adapters.PlaylistLibraryAdapter
import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.common.listeners.PlaylistListener
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentUserPlaylistsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FindPlaylistFragment : Fragment(),PlaylistListener {
    private lateinit var mBinding: FragmentUserPlaylistsBinding
    private lateinit var mPlaylistAdapter: PlaylistLibraryAdapter
    private lateinit var mLinearLayout: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentUserPlaylistsBinding.inflate(inflater, container, false)

        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerViews()
    }

    private fun setUpRecyclerViews(){
        playlistsRecyclerView()
    }

    private fun playlistsRecyclerView()
    {
        mPlaylistAdapter = PlaylistLibraryAdapter(this)
        mLinearLayout = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mPlaylistAdapter
        }

        getPlaylists()
    }

    private fun getPlaylists()
    {
        lifecycleScope.launch {
            try
            {
                val result = RetrofitClient.playlistService.getPlaylistByUser(Application.user.id)
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

    private fun showSnackbar(string: String)
    {
        Snackbar.make(mBinding.root, string, Snackbar.LENGTH_LONG).show()
    }

    override fun onClick(playlistEntity: PlaylistEntity) {
        lifecycleScope.launch {
            try {

                val result = RetrofitClient.playlistService.addSongToPlaylist(playlistEntity.id, Application.cancionId.toInt())

                val muestra = result.msg

                withContext(Dispatchers.Main)
                {
                    showSnackbar(muestra)
                    findNavController().navigate(R.id.action_userPlaylistsFragment_to_findFragment)
                }
            } catch(e: Exception)
            {
                showSnackbar(e.toString())
            }
        }
    }
}
