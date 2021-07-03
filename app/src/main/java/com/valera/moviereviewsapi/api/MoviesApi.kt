package com.valera.moviereviewsapi.api

import com.valera.moviereviewsapi.models.MovieData
import com.valera.moviereviewsapi.util.API_KEY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("reviews/search.json")
    suspend fun getMovies(@Query("api-key")  apiKey: String = "hJ37Ib9qF5XuD6GmcmeS3cOsV6NLUSfG") : Response<MovieData>

    companion object{
        operator fun invoke() : MoviesApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.nytimes.com/svc/movies/v2/")
                .build()
                .create(MoviesApi::class.java)
        }
    }
}