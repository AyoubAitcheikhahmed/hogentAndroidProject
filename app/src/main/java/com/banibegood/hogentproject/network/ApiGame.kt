package com.banibegood.hogentproject.network

import androidx.room.PrimaryKey
import com.banibegood.hogentproject.database.game.Game
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

//data class ApiGameContainer (
//    @Json(name="body")
//    val apiGames: List<ApiGame>
//    )


class ApiGame(
    val id : Long,
    val developer: String,
    @Json(name ="freetogame_profile_url")
    val freetogameProfileUrl: String,
    @Json(name = "game_url")
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    @Json(name ="release_date")
    val releaseDate: String,
    @Json(name ="short_description")
    val shortDescription: String,
    val thumbnail: String,
    val title: String
){
    public fun transform() : Game{
        val developer = this.developer
        val freetogameProfileUrl= this.freetogameProfileUrl
        val gameUrl = this.gameUrl
        val genre = this.genre
        val platform = this.platform
        val publisher = this.publisher
        val releaseDate = this.releaseDate
        val shortDescription = this.shortDescription
        val thumbnail = this.thumbnail
        val title = this.title
        val id = this.id
        return Game(
            developer,
            freetogameProfileUrl,
            gameUrl,
            genre,
            id,
            platform,
            publisher,
            releaseDate,
            shortDescription,
            thumbnail,
            title
        )
    }

}