package com.banibegood.hogentproject.database.friend

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends_table")
data class Friend(

    @PrimaryKey(autoGenerate = true)
    var id : Long,


    var lastName : String,

    var firstName : String,

    var achievements : Int
){}
