package com.krupnov.cryptoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinListOfData(
    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)
