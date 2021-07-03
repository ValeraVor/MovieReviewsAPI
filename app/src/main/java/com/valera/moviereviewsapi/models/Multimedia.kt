package com.valera.moviereviewsapi.models

import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("src")
    val src: String
)