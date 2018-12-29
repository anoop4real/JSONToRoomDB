package com.example.anoopm.jsontoroom.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AlbumDAO {

    @Query("SELECT * FROM albums")
    fun fetchall(): List<AlbumItem>

    @Query("SELECT * FROM albums WHERE title LIKE :title")
    fun loadAllStartsWith(title: String): List<AlbumItem>

    @Query("SELECT * FROM albums WHERE artist LIKE :name LIMIT 1")
    fun findByName(name: String): AlbumItem

    @Insert
    fun insert(albums: ArrayList<AlbumItem>)

    @Delete
    fun delete(album: AlbumItem)
}