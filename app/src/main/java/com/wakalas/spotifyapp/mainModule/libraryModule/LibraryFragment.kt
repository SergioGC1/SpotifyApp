package com.wakalas.spotifyapp.mainModule.libraryModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wakalas.spotifyapp.common.adapters.PlaylistHomeAdapter
import com.wakalas.spotifyapp.common.adapters.PlaylistLibraryAdapter
import com.wakalas.spotifyapp.common.utils.RetrofitClient
import com.wakalas.spotifyapp.databinding.FragmentLibraryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryFragment : Fragment()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView()
    {
        mPlaylistAdapter = PlaylistLibraryAdapter()
        mLinearLayout = LinearLayoutManager(requireContext())

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout
            adapter = mPlaylistAdapter
        }

        getPlaylists()
    }

    private fun getPlaylists() {
        lifecycleScope.launch {
            try {
                val result = RetrofitClient.playlistService.getPlaylists()

                val playlists = result.body()!!

                withContext(Dispatchers.Main) {
                    mPlaylistAdapter.submitList(playlists)
                }
            } catch(e: Exception)
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
