package com.valera.moviereviewsapi.ui.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.valera.moviereviewsapi.R
import com.valera.moviereviewsapi.adapters.MoviesAdapter
import com.valera.moviereviewsapi.api.MoviesApi
import com.valera.moviereviewsapi.repository.MoviesRepository
import com.valera.moviereviewsapi.ui.MainViewModel
import com.valera.moviereviewsapi.ui.MoviesViewModelFactory
import com.valera.moviereviewsapi.util.Resource
import kotlinx.android.synthetic.main.movies_fragment.*


class MoviesFragment : Fragment(R.layout.movies_fragment) {

    private lateinit var factory: MoviesViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val api = MoviesApi()
        val repository = MoviesRepository(api)

        factory = MoviesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    recycler_view_movies.also {
                        it.layoutManager = LinearLayoutManager(requireContext())
                        it.setHasFixedSize(true)
                        it.adapter = response.data?.let { it1 -> MoviesAdapter(it1.results) }
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}
