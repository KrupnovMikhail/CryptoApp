package com.krupnov.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.krupnov.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.krupnov.cryptoapp.utils.convertTimestampToTime


@Entity(tableName = "full_price_list")
data class CoinInfoDbModel (
     @PrimaryKey
     val fromSymbol: String,
     val toSymbol: String?,
     val price: Double?,
     val lastUpdate: Long?,
     val highDay: Double?,
     val lowDay: Double?,
     val lastMarket: String?,
     val imageUrl: String?,
 )