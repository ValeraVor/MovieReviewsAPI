package com.valera.moviereviewsapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.valera.moviereviewsapi.R
import com.valera.moviereviewsapi.databinding.ItemListBindingImpl
import com.valera.moviereviewsapi.models.Result

class MoviesAdapter (
    private val movies: List<Result>
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.movie = movies[position]
    }


    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: ItemListBindingImpl
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)

}