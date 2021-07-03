package com.valera.moviereviewsapi.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valera.moviereviewsapi.models.MovieData
import com.valera.moviereviewsapi.repository.MoviesRepository
import com.valera.moviereviewsapi.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(private val repository: MoviesRepository
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<MovieData>>()
    val movies: LiveData<Resource<MovieData>>
        get() = _movies

    fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            _movies.postValue(safeBreakingMoviesCall())
        }
    }

    private suspend fun safeBreakingMoviesCall() : Resource<MovieData> {
        _movies.postValue(Resource.Loading())
        try {
            val response = repository.getMovies()
            return handleBreakingMoviesResponse(response)

        } catch (t: Throwable) {
            when (t) {
                is IOException -> _movies?.postValue(Resource.Error("Network Failure"))
                else -> _movies?.postValue(Resource.Error("Conversion Error"))
            }
        }
        return Resource.Error("No internet connection")
    }


    private fun handleBreakingMoviesResponse(response: Response<MovieData>) : Resource<MovieData> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}