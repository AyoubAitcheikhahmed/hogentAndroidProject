package com.banibegood.hogentproject.database.friend

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FriendsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(friend : Friend)

    @Query("DELETE FROM friends_table WHERE id = :key")
    suspend fun delete(key: Long)

    @Query("SELECT * FROM friends_table ORDER BY id")
    suspend fun getAllFriends(): List<Friend>


}