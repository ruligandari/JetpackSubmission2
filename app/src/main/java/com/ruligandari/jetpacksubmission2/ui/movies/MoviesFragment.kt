package com.ruligandari.jetpacksubmission2.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ruligandari.jetpacksubmission2.viewmodel.ViewModelFactory
import com.ruligandari.jetpacksubmission2.databinding.FragmentMoviesBinding

class MoviesFragment: Fragment() {
    private var fragmentMoviesBinding: FragmentMoviesBinding?=null
    private val binding get() = fragmentMoviesBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[MoviesViewModel::class.java]
            val moviesAdapter = MoviesAdapter()
            showLoading(true)

            viewModel.getListPopularMovies().observe(viewLifecycleOwner, {
                if (it!=null){
                    moviesAdapter.setMovies(it)
                    showLoading(false)
                }
            })
            with(binding.rvMovies){
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMoviesBinding=null
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility= View.GONE
        }
    }

}