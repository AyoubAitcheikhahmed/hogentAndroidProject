package com.banibegood.hogentproject.model

data class Game(
    val name: String,
    val type: String,
    val desc: String,
    val image: Int,
    val price: Int? = null){


}
