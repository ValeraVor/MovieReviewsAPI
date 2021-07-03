package com.valera.moviereviewsapi.models

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("display_title")
    val display_title: String,
    @SerializedName("multimedia")
    val multimedia: Multimedia,
    @SerializedName("summary_short")
    val summary_short: String
)