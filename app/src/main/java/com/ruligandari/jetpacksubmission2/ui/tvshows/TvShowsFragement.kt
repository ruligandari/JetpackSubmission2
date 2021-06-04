package com.ruligandari.jetpacksubmission2.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruligandari.jetpacksubmission2.databinding.FragmentTvShowsBinding
import com.ruligandari.jetpacksubmission2.viewmodel.ViewModelFactory

class TvShowsFragement: Fragment() {
    private var fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = fragmentTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val viewModel = ViewModelProvider(this,ViewModelFactory.getInstance()).get(TvShowsViewModel::class.java)
            val tvShowsAdapter = TvShowsAdapter()
            showLoading(true)

            viewModel.getListPopularTvShows().observe(viewLifecycleOwner, {
                if (it != null){
                    tvShowsAdapter.setTvShows(it)
                    showLoading(false)
                }
            })

            binding.apply {
                rvTvShows.layoutManager = LinearLayoutManager(context)
                rvTvShows.setHasFixedSize(true)
                rvTvShows.adapter = tvShowsAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTvShowsBinding = null
    }
}