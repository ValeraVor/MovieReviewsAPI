package com.valera.moviereviewsapi.repository

import com.valera.moviereviewsapi.api.MoviesApi

class MoviesRepository(
    private val api: MoviesApi) {
    suspend fun getMovies() =  api.getMovies()
}