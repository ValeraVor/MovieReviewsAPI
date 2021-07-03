package com.valera.moviereviewsapi.models

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("results")
    val results: List<Result>
)