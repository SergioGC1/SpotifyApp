package com.wakalas.spotifyapp.mainModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wakalas.spotifyapp.databinding.FragmentFindBinding

class FindFragment : Fragment()
{
    private lateinit var mBinding: FragmentFindBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        mBinding = FragmentFindBinding.inflate(layoutInflater, container, false)

        return mBinding.root
    }
}
