package com.jakub.domain.models

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val isNew: Boolean,
    val isActive: Boolean,
    val type: String,
    val hashAlgorithm: String,
    val description: String,
    val platform: String
)
