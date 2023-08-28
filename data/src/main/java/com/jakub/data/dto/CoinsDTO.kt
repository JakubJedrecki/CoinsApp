package com.jakub.data.dto

import com.google.gson.annotations.SerializedName

data class ApiCoin(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("symbol") var symbol: String,
    @SerializedName("rank") var rank: Int,
    @SerializedName("is_new") var isNew: Boolean,
    @SerializedName("is_active") var isActive: Boolean,
    @SerializedName("type") var type: String,
    @SerializedName("description") var description: String,
    @SerializedName("hash_algorithm") var hashAlgorithm: String,
    @SerializedName("platform") var platform: String
)

