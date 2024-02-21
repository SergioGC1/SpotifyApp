package com.wakalas.spotifyapp.mainModule.libraryModule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wakalas.spotifyapp.Application
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.adapters.PlaylistLibraryAdapter
import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.common.entities.ResponseEntity
import com.wakalas.spotifyapp.common.listeners.PlaylistListener
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentLibraryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryFragment : Fragment(), PlaylistListener
{
    private lateinit var mBinding: FragmentLibraryBinding

    private lateinit var mPlaylistAdapter: PlaylistLibraryAdapter
    private lateinit var mLinearLayout: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        mBinding = FragmentLibraryBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setListeners()
    }

    private fun setListeners()
    {
        mBinding.fabAdd.setOnClickListener {
            showMenu()
        }
    }

    override fun onClick(playlistEntity: PlaylistEntity)
    {
        Application.playlist = playlistEntity
        goToSongFragment()
    }

    private fun setUpRecyclerView()
    {
        mPlaylistAdapter = PlaylistLibraryAdapter(this)
        mLinearLayout = LinearLayoutManager(requireContext())

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
            try {
                val result = RetrofitClient.playlistService.getPlaylistByUser(Application.user.id)

                val playlists = result.body()

                withContext(Dispatchers.Main)
                {
                    mPlaylistAdapter.submitList(playlists)
                }
            }
            catch(e: Exception)
            {
                withContext(Dispatchers.Main)
                {
                    showToast("Error al cargar las playlist")
                }
            }
        }
    }

    private fun showToast(text: String)
    {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun goToSongFragment()
    {
        findNavController().navigate(R.id.action_libraryFragment_to_songFragment)
    }

    private fun showMenu()
    {
        mBinding.fabAdd.visibility = View.GONE
        mBinding.newPlaylistMenu.visibility = View.VISIBLE

        setMenuListeners()
    }

    private fun setMenuListeners()
    {
        mBinding.btnCancel.setOnClickListener {
            hideMenu()
        }

        mBinding.btnAdd.setOnClickListener {
            postPlaylist()
        }
    }

    private fun postPlaylist()
    {
        val userId = Application.user.id
        val titulo = mBinding.etPlaylist.text.toString().trim()
        val playlist = PlaylistEntity(titulo = titulo)

        lifecycleScope.launch {
            try
            {
                Log.i("playlist", "$playlist")
                val result = RetrofitClient.playlistService.postPlaylist(userId, playlist)
                val response = result.body()

                withContext(Dispatchers.Main)
                {
                    showToast(response!!.msg)
                    hideMenu()
                }
            }
            catch(e: Exception)
            {
                withContext(Dispatchers.Main)
                {
                    Log.e("ERROR", "$e")
                    showToast("Error al crear la playlist")
                    hideMenu()
                }
            }
        }
    }

    private fun hideMenu()
    {
        mBinding.fabAdd.visibility = View.VISIBLE
        mBinding.newPlaylistMenu.visibility = View.GONE
    }
}
